package thomas.spring;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
public class JwtUtils {
    //7 days expire
    private static long expire = 604800;
    //32 bit secret
/*
    io.jsonwebtoken.security.WeakKeyException: The signing key's size is 192 bits which is not secure enough for the HS512 algorithm.
    The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with HS512 MUST have a size >= 512 bits (the key size must be greater than or equal to the hash output size).
    Consider using the io.jsonwebtoken.security.Keys class's 'secretKeyFor(SignatureAlgorithm.HS512)' method to create a key guaranteed to be secure enough for HS512.
    See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.
*/

    private static String secret = "abcdfghiabcdfghiabcabcdfghiabcdfabcdfghiabcdfghiabcabcdfghiabcdfghiabcdfghiabcdfghidfghiabcdfghighiabcdfghiabcdfghidfghiabcdfghi";

    public static String generateToken(String username){
        log.info(Keys.secretKeyFor(SignatureAlgorithm.HS512).toString());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Claims getClaimsByToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
