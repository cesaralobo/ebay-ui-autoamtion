# Beletrix Exam - Ebay

Selenium, Java, testNG automation framework and Extend reports.

Requirements :

To run this project you will need to have installed in your machine:

- JDK 1.8 or higher
- Maven 3.0 or higer
- Extend Report 3

Maven Execution:

"mvn clean test -Dsuite= TESTNG_SUITE_TO_RUN -Denvironment= ENVIRONMENT_TO_RUN_AGAINST"



## Page object pattern <h2>

The page object pattern is based on the creation of an object for each one of the important pages in our web site, in this way we will be able to reuse the page object’s elements in other tests.
As an example, the menu of a site is an element that will be used in many tests, so we must create a page object where we could define each element (button, link, input) of the menu.
When an element of a page object loads to another page, we must create a new page object for this page and make that the element in first page object redirect to the new page. (IE: a ‘login’ button that loads to a new page)

## Locators <h2>

The locators find and match elements of a web page to interact with them. The most common are: Id, Name, Link Text, XPath and CSS Selector.

**ID Locator**

ID is the simplest way to find an element in a web page through an ID which is the only in the page.
Format: id= “id of the element”


**Name Locator**

Name locator is similar to find an element by ID, the only difference is that the name of the element will be used.
Format: name = “name of the element”

**Link Text Locator**

This locator is used only to hyperlink texts, with this we can find elements of ‘a’ tags with the name
 Format: link= “name of the link”

**XPath Locator**

XPaths locators are the most robust locators, it can access almost any element, even those without class, name, or ID.
Format: Xpath=//tagname[@attribute='value']
- // : Select current node.
- Tagname: Tagname of the particular node (input, div, form, etc).
- @: Select attribute.
- Attribute: Attribute name of the node.
- Value: Value of the attribute.

**CSS Locator**

CSS selectors has some benefits, it is faster, more readable and used more often. In CSS we use # notation to select the id, also we use the . notation to select the class.
CSS selectors allow us to select by class and style if we want to be more accurate
Generic format: css = element_name[<attribute_name>='<value>']
 
 
## Waits <h2>

**Implicit Wait**

We can set that we would like to wait for a certain amount of time before throwing an exception while is trying to find an element or a set of elements, the implicit waits will be in place for the entire time the browser is open.
 
**Explicit Wait**

You define a wait for a certain condition or the maximum time exceeded before continue in the code or throwing an exception.
 
**Fluent Wait**

A fluent wait instance defines the maximum amount of time to wait for a condition, as well as the frequency to check the condition.

**Difference between Implicit, Explicit and Fluent wait**
 
In an implicit wait, if the Webdriver cannot find an element, it continues waiting around 250 milliseconds to get the element. The default time is zero, when you set a time, the web driver waits for this time until the element appears.
Whereas in an explicit time exist a particular element who needs a specific time to appears, so you have to set a huge wait, but if you don’t want to define it for your hole test, you can define it only for a specific element.
Sometimes, we have an element that appears in a second or in a few minutes. In that case use a fluent wait could be the solution, you can set a final time, and check for the presence of the element during this time.

## Best practices <h2>

- A page object should not have any assertions.
- Verify that a page is ready before interacting with it.
- Try to parameterize as much as your site allows you, this method will help you in a next version of the site having a robust test.
- Verify if the element is present/visible to interact with it.
- All dependencies with Selenium should reside in the page objects, not in the tests. 

## TestNG <h2>

TestNG is a framework based on Junit and NUnit, but it introduces some new functionalities that make it easier to use and more powerful. TestNG cover all categories of test: unit, functional, integration, etc.

A test Suite is a collection of test, in testing we can define this suite represented by one XML file. A suite can contain one or more tests, defined by the <suite> tag, in this we can find several <test> sections.

If you want to run only a test and not a package or test, you will able to create a class where you can specify the test that you want to run.

## Utils <h2>
In this project, you can find some utils, as an example, ExcelUtils provides you some tools that could help you in your project, you will find functions as open an Excel file, count the number of rows, get the value of them, and print in a new Excel file, the result of you test, so in case of an error, you will be able to identify the exact moment and row where the test fail.
 


