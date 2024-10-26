package io.userauth.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.userauth.constant.TokenStatus;
import io.userauth.data.repositories.RefreshTokenRepository;
import io.userauth.models.RefreshToken;
import jakarta.transaction.Transactional;

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
        refreshTokenRepository.addToken(newRefreshToken);
        return newRefreshToken.getToken();
    }

    @Override
    @Transactional
    public void revokeToken(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        refreshToken.setStatus(TokenStatus.REVOKED);
    }

    @Override
    public UUID getUserIdIssuer(UUID token) {
        RefreshToken refreshToken = refreshTokenRepository.findTokenById(token);
        return refreshToken.getUserId();
    }


    private Date calculateDays(Date issuedAt,int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issuedAt);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

}
