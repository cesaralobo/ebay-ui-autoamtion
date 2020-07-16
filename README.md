# Ebay UI Automation

Selenium, Java, testNG automation framework and Extend reports.

Requirements :

To run this project you will need to have installed in your machine:

- JDK 1.8 or higher
- Maven 3.0 or higer
- Extend Report 3
- Chrome or Firefox Browser (last version)

```  
$ git clone git@github.com:cesaralobo/ebay-ui-automation.git 
```  

## Case Steps <h2>

1. Enter to Ebay https://www.ebay.com/
2. Search for shoes
3. Select brand PUMA
4. Select status “New with box”
5. Print the number of results
6. Order by price ascendant
7. Assert the order taking the first 5 results
8. Take the first 5 products with their prices and print them in console.
9. Order and print the products by name (ascendant)
10. Order and print the products by price in descendant mode
11. Repository must be created in any git place (github, bitbucket, etc)
12. Code must run in any CI tool.
13. Report should be sent by mail.

## Best practices <h2>

- A page object should not have any assertions.
- Verify that a page is ready before interacting with it.
- Try to parameterize as much as your site allows you, this method will help you in a next version of the site having a robust test.
- Verify if the element is present/visible to interact with it.
- All dependencies with Selenium should reside in the page objects, not in the tests. 

 


