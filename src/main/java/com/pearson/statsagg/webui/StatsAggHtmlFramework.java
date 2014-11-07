package com.pearson.statsagg.webui;

import java.net.URLEncoder;
import java.util.List;
import java.util.Set;
import com.pearson.statsagg.globals.ApplicationConfiguration;
import com.pearson.statsagg.utilities.KeyValue;
import com.pearson.statsagg.utilities.StackTrace;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class StatsAggHtmlFramework {

    private static final Logger logger = LoggerFactory.getLogger(NotificationGroupsLogic.class.getName());
    
    public StatsAggHtmlFramework() {}
    
    public String createHtmlHeader(String title, String additionalHtmlToInjectIntoHeader) {
        
        StringBuilder header = new StringBuilder("");
        
        header.append("<head>\n");
        
        header.append("<meta charset=\"utf-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<meta name=\"description\" content=\"StatsAgg\">\n" +
                    "<meta name=\"author\" content=\"Jeffrey Schmidt\">\n");
    
        if (title != null) {
            header.append("\t<title>").append(title).append("</title>\n");
        }
        
        header.append("<link rel=\"icon\" href=\"favicon.ico\" type=\"image/x-icon\">\n");
        header.append("<link rel=\"shortcut icon\" href=\"favicon.ico\" type=\"image/x-icon\">\n");

        header.append("<link href=\"css/bootstrap.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/bootstrap-theme.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/bootstrap-datetimepicker.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/datepicker.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/dataTables.bootstrap.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/dataTables.colVis.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"css/colorbox.css\" rel=\"stylesheet\">\n" +
                    "<link href=\"font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\">" +
                    "<link href=\"css/statsagg.css\" rel=\"stylesheet\">\n");
        
        if (additionalHtmlToInjectIntoHeader != null) {
            header.append(additionalHtmlToInjectIntoHeader);
        }
        
        header.append("</head>\n");
        
        return header.toString();
    }
    
    public String createHtmlBody(String additionalHtmlToInjectIntoBody) {
        
        StringBuilder body = new StringBuilder("");
        
        body.append("<body>\n");

        body.append("" +
                "<nav class=\"statsagg_nav_bar navbar navbar-default navbar-static-top\" role=\"navigation\"> \n" +
                "  <div class=\"navbar-header pull-left\"> \n" +
                "    <a class=\"statsagg_nav_bar_home_link navbar-brand\" href=\"index.html\">StatsAgg</a> \n" +
                "  </div> \n" +
                
                "  <div class=\"navbar-header navbar-right pull-right\"> \n" +
                "    <ul class=\"nav navbar-nav\">" +
                
                "      <li><a href=\"Home\"><i class=\"fa fa-dashboard\"></i> Home</a></li>\n" +
                
                "      <li class=\"dropdown\">\n" +
                "        <a class=\"dropdown-toggle\" href=\"#\"> Alerting&nbsp;<i class=\"fa fa-caret-down\"></i> </a>\n" +
                "        <ul class=\"dropdown-menu dropdown-menu-right\">\n" +
                "          <li><a href=\"Alerts\"><i class=\"fa fa-exclamation-triangle\"></i>&nbsp;&nbsp;Alerts </a></li>\n" +
                "          <li><a href=\"AlertSuspensions\"><i class=\"fa fa-moon-o\"></i>&nbsp;&nbsp;Alert Suspensions </a></li>\n" +
                "          <li><a href=\"MetricGroups\"><i class=\"fa fa-align-justify\"></i>&nbsp;&nbsp;Metric Groups </a></li>\n" +
                "          <li><a href=\"NotificationGroups\"><i class=\"fa fa-envelope\"></i>&nbsp;&nbsp;Notification Groups </a></li>\n" +
                "        </ul> \n" +
                "      </li>\n" +

                "      <li class=\"dropdown\">\n" +
                "        <a class=\"dropdown-toggle\" href=\"#\"> Actions&nbsp;<i class=\"fa fa-caret-down\"></i> </a>\n" +
                "        <ul class=\"dropdown-menu dropdown-menu-right\">\n");
        
        body.append("<li><a href=\"RegexTester\"><i class=\"fa fa-check-circle\"></i>&nbsp;&nbsp;Rexex Tester</a></li>\n");
        body.append("<li><a href=\"ForgetMetrics\"><i class=\"fa fa-eraser\"></i>&nbsp;&nbsp;Forget Metric(s) </a></li>\n");

        for (HttpLink httpLink : ApplicationConfiguration.getCustomActionUrls()) {
            if ((httpLink.getUrl() == null) || httpLink.getLinkText().isEmpty() || (httpLink.getUrl() == null) || httpLink.getUrl().isEmpty()) continue;
            body.append("<li><a href=\"").append(httpLink.getUrl()).append("\"><i class=\"fa fa-external-link\"></i>&nbsp;&nbsp;").append(httpLink.getLinkText()).append("</a></li>\n");
        }
                
        body.append(
                "        </ul> \n" +
                "      </li>");
        
        body.append("      <li><a href=\"./docs/Manual.htm\"><i class=\"fa fa-question-circle\"></i></a></li>\n");

        body.append(
                "    </ul>\n" +
                "  </div>\n" +
                "</nav>\n");
        
        String additionalHtmlToInjectIntoBodyLocal = "";
        if (additionalHtmlToInjectIntoBody != null) {
            additionalHtmlToInjectIntoBodyLocal = additionalHtmlToInjectIntoBody;
        }
        
        body.append(additionalHtmlToInjectIntoBodyLocal);
                
        body.append(""
                + "<!-- JavaScript -->\n"
                + "<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/moment.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/jquery.colorbox-min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/jquery.dataTables.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/dataTables.bootstrap.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/jquery.dataTables.columnFilter.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/dataTables.colVis.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/bootstrap-datetimepicker.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/bloodhound.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/bootstrap3-typeahead.min.js\"></script>\n"
                + "<script type=\"text/javascript\" src=\"js/statsagg.js\"></script>\n"
        );
        
        body.append("</body>\n");
        
        return body.toString();
    }
    
    public String buildHtmlBodyForPostResult(String pageName, String innerHtmlContentBody, String returnLinkLocation, String returnLinkDescription) {
        
        StringBuilder htmlBody = new StringBuilder("");
        
        htmlBody.append(
            "<div id=\"page-content-wrapper\">\n" +
            "  <!-- Keep all page content within the page-content inset div! -->\n" +
            "  <div class=\"page-content inset\">\n" +
            "  <div class=\"content-header\"> \n" +
            "    <h2> " + pageName + " Results" + " </h2> \n" +
            "  </div> ");
        
        if (innerHtmlContentBody != null) {
            htmlBody.append(innerHtmlContentBody);
            htmlBody.append("<br>");
            htmlBody.append("<br>");
        }
        
        htmlBody.append("<a href=\"").append(returnLinkLocation).append("\"> " + "Return to ").append(returnLinkDescription).append("</a>");
        
        htmlBody.append("</div></div>");
        
        return htmlBody.toString();
    }
    
    public static String buildJavaScriptPostLink(String plaintextFormId, String postEndpoint, String linkText, List<KeyValue> postParameters) {
        return buildJavaScriptPostLink(plaintextFormId, postEndpoint, linkText, postParameters, false, null);
    }
    
    public static String buildJavaScriptPostLink(String plaintextFormId, String postEndpoint, String linkText, List<KeyValue> postParameters, boolean confirmAction, String confirmationText) {
        
        if (plaintextFormId == null) {
            return null;
        }
        
        StringBuilder stringBuilder = new StringBuilder("");
        
        String encodedFormId = "F_"  + DigestUtils.md5Hex(plaintextFormId);
        
        stringBuilder.append("<form action=\"").append(postEndpoint).append("\" method=\"POST\" style=\"display: inline; margin-right: -4px;\" id=\"")
                .append(encodedFormId).append("\" name=\"").append(encodedFormId).append("\">");

        for (int i = 0; i < postParameters.size(); i++) {
            stringBuilder.append(" <input type=\"hidden\" ");
            stringBuilder.append("name=\"").append(postParameters.get(i).getKey()).append("\" value=\"").append(postParameters.get(i).getValue());
            stringBuilder.append("\"/> ");
        }
        
        stringBuilder.append(" ");
        
        if (confirmAction) {
            stringBuilder.append("<a href=\"#\" onclick=\"confirmAction('").append(encodedFormId).append("', '").append(confirmationText).append("')\">").append(linkText).append("</a>");
        }
        else {
            stringBuilder.append("<script type=\"text/javascript\">document.write('<a href=\"#")
                    .append("\" onclick=\"document.forms[\\'").append(encodedFormId).append("\\'].submit(); return false;\">").append(linkText).append("<\\/a>');</script>");
        }
        
        stringBuilder.append(" </form>");
        
        return stringBuilder.toString();
    }
    
    public static String createCloneName(String originalName, Set<String> names) {
        
        if (originalName == null) {
            return null;
        }
        
        if (names == null) {
            return originalName + "_" + 1;
        }

        String outputString = null;
        
        for (int i = 1; outputString == null; i++) {
            String testUnqiueString = originalName + "_" + i;
            
            if (!names.contains(testUnqiueString)) {
                outputString = testUnqiueString;
            }
        }
        
        return outputString;
    }
    
    public static String urlEncode(String inputUrlSnippet) {
        
        if (inputUrlSnippet == null) {
            return null;
        }
        
        String encodedUrlSnippet = "";
        
        try {
            encodedUrlSnippet = URLEncoder.encode(inputUrlSnippet, "UTF-8");
        }
        catch (Exception e) {
            logger.error(e.toString() + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));
        }
        
        return encodedUrlSnippet;
    }
    
    public static String htmlEncode(String unencodedString) {
        if (unencodedString == null || unencodedString.isEmpty()) {
            return unencodedString;
        }
        
        String htmlEscapedString = StringEscapeUtils.escapeHtml(unencodedString);
        htmlEscapedString = StringUtils.replace(htmlEscapedString, " ", "&nbsp;");
        return htmlEscapedString;
    }

    public static String removeNewlinesFromString(String inputString) {

        if ((inputString == null) || inputString.isEmpty()) {
            return inputString;
        }
        
        String cleanedString = StringUtils.remove(inputString, '\r');
        cleanedString = StringUtils.remove(cleanedString, '\n');

        return cleanedString;
    }
    
    public static String removeNewlinesFromString(String inputString, char newlineReplacementCharacter) {

        if ((inputString == null) || inputString.isEmpty()) {
            return inputString;
        }
        
        String cleanedString = inputString.replace('\n', newlineReplacementCharacter).replace('\r', newlineReplacementCharacter);
        return cleanedString;
    }
    
}
