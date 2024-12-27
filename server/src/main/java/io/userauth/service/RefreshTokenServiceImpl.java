package io.userauth.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.userauth.constant.TokenStatus;
import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.models.RefreshToken;
import io.userauth.presentation.exception.RefreshTokenErrorCode;
import io.userauth.presentation.exception.RefreshTokenException;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${refresh.token.expiry.days}")
    private int refreshTokenExpiryDays;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public UUID generateToken(UUID userIdIssuer) {
        RefreshToken newRefreshToken =  new RefreshToken();
        newRefreshToken.setToken(UUID.randomUUID());
        newRefreshToken.setUserId(userIdIssuer);

        Date issuedAt = new Date();
        Date expirateAt = calculateDays(issuedAt, refreshTokenExpiryDays);

        newRefreshToken.setIssuedAt(issuedAt);
        newRefreshToken.setExpiredAt(expirateAt);
        refreshTokenRepository.save(newRefreshToken);
        return newRefreshToken.getToken();
    }

    @Override
    @Transactional
    public void revokeToken(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        refreshToken.setStatus(TokenStatus.REVOKED);
    }

    @Override
    @Transactional
    public UUID getUserIdIssuer(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        return refreshToken.getUserId();
    }

    
    @Override
    @Transactional
    public UUID consumeToken(UUID token) throws RefreshTokenException {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);

        if (refreshToken == null) {
            throw new RefreshTokenException(RefreshTokenErrorCode.TOKEN_NONEXISTENCE,"Token not found");
        }


        if (new Date().after(refreshToken.getExpiredAt())) {
            refreshToken.setStatus(TokenStatus.EXPIRED);
            throw new RefreshTokenException(RefreshTokenErrorCode.TOKEN_EXPIRED, "expired Session");
        }

        if (refreshToken.getStatus() != TokenStatus.ACTIVE) {
            throw new RefreshTokenException(RefreshTokenErrorCode.fromTokenStatus(refreshToken.getStatus()),"invalid session, please login again");
        }
        
        refreshToken.setStatus(TokenStatus.USED);
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getUserId();

    }

    private Date calculateDays(Date issuedAt,int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issuedAt);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

}
