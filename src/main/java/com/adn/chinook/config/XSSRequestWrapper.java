package com.adn.chinook.config;

/**
 *
 * @author CAGECFI
 */
public class XSSRequestWrapper //        extends HttpServletRequestWrapper
{

//    public XSSRequestWrapper(HttpServletRequest request) {
//        super(request);
//    }
//
//    @Override
//    public String[] getParameterValues(String parameter) {
//        String[] values = super.getParameterValues(parameter);
//        if (values == null) {
//            return null;
//        }
//        int count = values.length;
//        String[] encodedValues = new String[count];
//        for (int i = 0; i < count; i++) {
//            encodedValues[i] = XSSUtils.stripXSS(values[i]);
//        }
//        return encodedValues;
//    }
//
//    @Override
//    public String getParameter(String parameter) {
//        String value = super.getParameter(parameter);
//        return XSSUtils.stripXSS(value);
//    }
//
//    @Override
//    public Enumeration getHeaders(String name) {
//        List result = new ArrayList<>();
//        Enumeration headers = super.getHeaders(name);
//        while (headers.hasMoreElements()) {
//            String header = headers.nextElement().toString();
//            String[] tokens = header.split(",");
//            for (String token : tokens) {
//                result.add(XSSUtils.stripXSS(token));
//            }
//        }
//        return Collections.enumeration(result);
//    }
}
