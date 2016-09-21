## Introduction

jeeplus is an open-source, e-commerce framework written entirely in Java on top of the Spring framework. It is targeted at facilitating the development of enterprise-class, commerce-driven sites by providing a robust data model, services and specialized tooling that take care of most of the "heavy lifting" work. To accomplish this goal, we have developed our platform based on the key feature sets required by world-class online retailers - and we're committed to continually expanding our feature offering. We've also taken extra steps to guarantee interoperability with today's enterprise by utilizing standards wherever possible and incorporating best-of-breed, open-source software libraries from the community.

## Getting Started

## Support

## Key Features and Technologies

### Spring Framework
Spring is the enterprise Java platform on which jeeplus is based.  It provides numerous features, including dependency injection and transaction control.

### Security
Spring Security provides a robust security framework for controlling authentication and authorization at both the code and page level and is utilized by jeeplus for access control.

### Persistence
Mybatis represent the jeeplus ORM infrastructure for 
controlling persistence of our rich domain model.

### Search
Flexible domain search capabilities in jeeplus are provided through integration
with Solr.

### Task Scheduling
Scheduling of repetitive tasks in jeeplus is offered through the 
Quartz job scheduling system.

### Email
Email support is provided throughout the jeeplus framework in either synchronous 
or asynchronous (JMS) modes. Email presentation customization is achieved via Thymeleaf templates.

### Modular Design
Important e-commerce touchpoints are embodied in the concept of jeeplus 
"Modules". A module can provide interaction with a credit card processor, or even a shipping provider. 
Any number of custom modules may be developed and utilized with jeeplus.

### Configurable Workflows
Key areas in the e-commerce lifecycle are represented as configurable 
workflows. Implementors have full control over the keys steps in pricing and checkout, allowing 
manipulation of module ordering, overriding existing module behavior and custom module execution. 
Composite workflows are also supported to achieve more exotic, nested behavior.

### Extendible Design
jeeplus is designed from the ground-up with extensibility in mind. 
Almost every aspect of jeeplus can be overridden, added to or otherwise modified to enhance 
or change the default behavior to best fit your needs. This includes all of our services, data access 
objects and entities. Please refer to the extensibility section of our documentation.

### Configuration Merging
As an extra bonus to our extensibility model, we offer a custom merge facility 
for Spring configuration files. We minimize the jeeplus configuration semantics that an 
implementer must be aware of, allowing our users to focus on their own configuration particulars. 
jeeplus will intelligently merge its own configuration information with that provided by 
the implementer at runtime.

### Presentation Layer Support
jeeplus also includes a number of pre-written Spring MVC 
controllers that help to speed development of the presentation layer of your own jeeplus-driven 
site.

### QOS
jeeplus also provides quality of service monitoring for modules (both custom and 
default modules) and provides support for several QOS handlers out-of-the-box: logging and email. 
Additional, custom QOS handlers may be added through our open API.

### Promotion System
jeeplus includes a highly-configurable system for including your pricing 
promotions. We provide several standard levels at which promotions may be applied: Order level, Order 
Item level and Fulfillment Group level. In addition, your promotion business rules are represented in 
a flexible and standardized way using the MVEL expression language.

### PCI Considerations
We have taken measures in the construction and design of jeeplus to 
help you achieve PCI compliance, should you decide to store and use sensitive customer financial 
account information. Payment account information is referenced separately, allowing you to segregate 
confidential data onto a separate, secure database platform. API methods have been added to allow 
inclusion of any PCI compliant encryption scheme. Also, verbose logging is included to track payment 
interaction history.

### Admin Platform
jeeplus includes a wholely extendible administrative application built with Spring MVC. The admin application also provides an easy-to-use interface
for catalog, order and customer functions and provides a robust, rule-driven environment for creating
and managing discount promotions.

### Admin Customization
jeeplus provides a robust set of admin presentation annotations that allow configuration of domain
class display and persistence semantics without touching any admin code. This provides an easy-to-consume approach
for introducing entity extensions and additional fields into the admin forms so that your business users can immediately
start to benefit. We also provide a full annotation or xml-based approach for overriding the admin config declared
inside jeeplus so that you can have an impact on our defaults. And for more advanced customizations, our admin
platform is based on Spring MVC, so your Spring knowledge will translate here as well when it comes to adding additional
controllers, and the like.

### Content Management
jeeplus includes a robust content management system for creating and
managing static pages and content. We also include a powerful content targeting feature that allows
business users to dynamically drive the most appropriate content to users.

## Contributing
See [CONTRIBUTING.md](CONTRIBUTING.md)

## License

jeeplus  core is released under the terms of the Apache Software License 2 (see license.txt). However, various commercial modules that are also available (for instance, price list management) are released under a different commercial license. These are not included with the core jeeplus framework.

