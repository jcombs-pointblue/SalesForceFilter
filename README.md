# SalesForceFilter
Servlet filter to add Lightning component namespaces to OAuth responses

Salesforce Lightening components are isolated from web page where they are deployed through the use of a “namespace”
that must be used when the code references objects, functions, and controllers.  The namespace is added as a prefix 
to the reference.  A component can define it’s own private namespace but if it does not then a default ,“c”, namespace is used . 
Because of this, URL query parameters like those used for OAuth must be prefixed with the proper namespace to be visible to the component.  

The authentication server sends authentication responses using the standard OAuth parameter names. To make these responses available to 
Lightning Components, the parameter names must be changed before the reply is sent. This filter was developed for use with Microfocus Access Manager
but it should work for other authentication servers that run in servlet container.

The filter must be configured in web.xml and the relative position of the filter is important. For Microfocu Access Manager
the filter should be placed just before the DebugFilter. The configuration should be similar to the one shown below:

      <filter>
           <filter-name>SalesForceFilter</filter-name>
           <filter-class>com.pointblue.sf.filter.Wrapper</filter-class>
           <init-param>
               <param-name>requestStringToMatch</param-name>
               <param-value>lightning.force.com</param-value>
           </init-param>
          <init-param>
               <param-name>queryParamPrefix</param-name>
               <param-value>c__</param-value>
           </init-param>
        </filter>
        <filter-mapping>
            <filter-name>SalesForceFilter</filter-name>
            <url-pattern>/oauth/nam/authz</url-pattern>
        </filter-mapping>
