# Skroutz price scraper

*Description:*

A java application that tracks the lowest price of a product you selected from Skroutz official website and informs you with an email when the price reaches the desired amount,that you selected.



 **Setup**
 
 - Replace example@gmail.com from every string with your email 
 
 ```java
 String to = "example@gmail.com";
 String from = "example@gmail.com";
 
 ```
- Create a Google app password and paste it: 

```java
final String password = "password";
```
>visit this <a href="https://devanswers.co/create-application-specific-password-gmail/" target="_blank">`link`</a>
 for further information on how to generate app password.
 
 - Copy the product link from Skroutz official website and paste it here : 
 
 ```java
String URL = "skroutz poduct link e.g: https://www.skroutz.gr/s/10864394/Nintendo-Switch-Red-Blue-Joy-Con-32GB.html?from=featured";
 ```
 
 - Set the desired price **⚠️USE DECIMAL NUMBERS⚠️**
 ```java
 double desiredPrice =130.50;
 ```
 
