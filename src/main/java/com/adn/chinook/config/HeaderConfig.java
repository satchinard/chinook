package com.adn.chinook.config;

import java.util.Collections;
import java.util.List;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.context.request.NativeWebRequest;

/**
 *
 * @author cagecfi
 */
public class HeaderConfig implements ContentNegotiationStrategy {

    private static final String ACCEPT_HEADER = "Accept";

    @Override
    public List<MediaType> resolveMediaTypes(NativeWebRequest nwr) throws HttpMediaTypeNotAcceptableException {
        String acceptHeader = nwr.getHeader(ACCEPT_HEADER);
        try {
            if (StringUtils.hasText(acceptHeader)) {
                List<MediaType> mediaTypes;
                mediaTypes = MediaType.parseMediaTypes(acceptHeader);
                MediaType.sortBySpecificityAndQuality(mediaTypes);
                return mediaTypes;
            }
        } catch (InvalidMediaTypeException ex) {
            throw new HttpMediaTypeNotAcceptableException(
                    "Ne peut lire l'entête de la demande [" + acceptHeader + "]: " + ex.getMessage());
        }
        return Collections.emptyList();
    }

}
