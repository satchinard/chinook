package com.adn.chinook.config;

/**
 *
 * @author CAGECFI
 */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class XSSFilter //        implements Filter
{

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest) request);
//        String body = IOUtils.toString(wrappedRequest.getInputStream());
//        if (!StringUtils.isBlank(body)) {
//            body = XSSUtils.stripXSS(body);
////            wrappedRequest.resetInputStream(body.getBytes());
//        }
//        chain.doFilter(wrappedRequest, response);
//    }
}
