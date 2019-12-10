Bean & Leaf v1.01

Start the project by clicking the run button in Android Studio. 

The app automatically takes you to the login page, where you can login or create a new account.

Clicking the register button will prompt you to either create a Student or Merchant account.

Students are able to view the map and select a store, where they can view its menu and add items to their bag.

Merchants have all of the capabilities of students but can also create/manage their own store(s).

The following features will be implemented in Bean & Leaf v1.02:

Since both merchants and students can "order" from coffee/tea shops on the map, every account should be able to
view their order history. This functionality is still in development.

The menu is currently hard-coded, so every store has the same basic menu. We are still developing
the ability to add new items or create customizable menus yet.

The directions to a selected store are still in development.

**Product Backlog**    
We implemented the following features:
1. If a merchant registers without a menu, then no menu items will load when accessing the Menu page and an error message appears.  
2. When the user gets the business license from his/her phone, it will be uploaded to the database for professional review from Bean and Leaf Inc.  
3. When checking out, make sure that 1+ items were selected. Otherwise, the page will display an error message. There should be an option to go back.  
4. Create separate menu page for merchant that simply displays the text  
5. Add a singleton class to store user information so that we can remove the intent.putExtra() functionality.
6. Create a database class to store all database functions.  
7. Add an app logo.  
8. Remove adding first menu item from registration  
9. Restructure menu class to allow merchant in database without any menu items in existence.  
10. Display Toast message when user arrives at the store and sees the menu.  
11. After the user logs in, going back should log out the user  
12. After a drinker successfully logs in, the app should not display Toast message with “incorrect password”  
13. After a merchant successfully logs in, the app should not display Toast message with “incorrect password”  
14. After a drinker successfully logs in, the app should not display Toast message with “incorrect password”  
15. After a merchant successfully logs in, the app should not display Toast message with “incorrect password”  
16. Change manage profile UI for merchants  
17. Change color of menu buttons to white  
18. Change color of business buttons to white  
19. Create order history backend with changing class time functions  
20. Change store profile UI so that it includes customer spending  
21. When the user selects items from the menu, it gets added to the database and shown in the checkout window. It will also be included in the order history.  
