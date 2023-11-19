# [G27 - HHHHDDDD] Report



## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#code-design-and-decision)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#meeting-records)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)



# **Team Members and Roles**

| *UID*    | *Name* | *Role*                           |
| -------- | -------------- | ---------------------------------------- |
| U7574421 | Simon Fu       | Overall UI design and Firebase integrate |
| U7544341 | Zehua Liu      | Program Developer (Search)               |
| U7618768 | Connor Li      | Friends list and P2P message             |
| U7587847 | Yongsong Xiao  | Test basic UI and foundation             |

 

# **Summary of individual contributions**

**A)** **U7574421, Simon Fu** - I have 29% contribution, as follows:

- **Code contribution in the final app**
  - **Base class design** - Class User: [User.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/User.java), Class Game: [Game.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Game.java)
  - **Login and sign up design** - Class SignActivity: [SignActivity.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignActivity.java), Class LoginFragment:[LoginFragent.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/LoginFragment.java), Class SignupFragment: [SignupFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignupFragment.java), Class SignInAdapter: [SignInAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignInAdapter.java).
  - **[Firebase database integration](https://console.firebase.google.com/project/comp6442database/overview)**
  - **Library, game list and profile design** - Class MyLibrary: [MyLibrary.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/MyLibrary.java), Class MyWishlist:[MyWishlist.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/MyWishlist.java), Class LibraryListAdapter: [LibraryListAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/LibraryListAdapter.java), Class ProfileFragment: [ProfileFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Profile/ProfileFragment.java).

- **Code and App Design**

  - **Data Structure**: Arraylist.

  - **Design pattern**: Singleton, Observer and Adapter([SignInAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignInAdapter.java), [LibraryListAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/LibraryListAdapter.java), [MenuAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/MenuAdapter.java), [NewsGameAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/News/NewsGameAdapter.java), [GameListAdapter](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListAdapter.java)).
  - **UI Design**: Design UI for [main interface](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/MainInterface.java), [login](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/LoginFragment.java) and [signup](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignupFragment.java) interface, [news](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/News/NewsFragment.java), [game detail](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameDetail.java), [game 	list](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java) ,[library](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/MyLibrary.java), [wishlist](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/MyWishlist.java) and [profile](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Profile/ProfileFragment.java).

* **Others**
  - **Report Writing**: Organize report structure and write non-individual part of report, including 	UML diagram, application description, and conflict resolution protocol.
  - **Feature Video**: Record the features video.

**B)** **U7618768, Connor Li** - I have 24% contribution, as follows:

- **Code contribution in the final app**
  - **Friends list degisn** - Class: FriendsListActivity.java, FriendsListAdapter.java.
  - **Peer to peer chat function design** - Class: ChatMessage.java, ChatActivity.java, ChatAdapter.java.
  - **AVLTree degisn** - Class: AVLTree.java.
  
- **Code and App Design**
  - **Design Pattern**: Observer, Adapter.
  - **Data Structure**: ArrayList.
  - **UI Design**: Design UI for friendslist, Friend information and status display and Chat interface



**C)** **U7544341, Zehua Liu** - I have 24% contribution, as follows:

- **Code contribution in the final app**
  - **Search Feature Design** - Class Tokenizer: [Tokenizer.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Tokenizer.java), Class GameListFragment: [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L111-170) (Method: SetOnClickListener())
  - **Invalid Input Search Feature Design** - Class Tokenizer: [Tokenizer.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Tokenizer.java), Class GameListFragment: [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L111-170) (Method: SetOnClickListener())
  - **Modification of AVLTree** - Class AVLTree: [AVLTree.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/AVLTree.java#L189-245) (Method: inOrderToList(), inOrderToList())
  - **Use of AVLTree** - refer to data structure part.

* **Others**
  - **(Json File) Game Data**: Created and modified gameData (Data moved to firebase later) [data.json](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/commit/ecad8aa278ea716d0172f720872b087d62a7aba9#7192d8aaea896a7b9856b97f2c50cc93eca68b6c)
  - **Game Data Detail**: Search for 500+ game images of 250+ games manually.
  - **Firebase**: Import 500+ game images for 250+ games to Firebase Realtime Database manually. ([Firebase Realtime Database](https://console.firebase.google.com/project/comp6442database/database/comp6442database-default-rtdb/data?hl=zh-cn)) (500+ links for "url" and "url_l" component for each game)
  - **Minute Madness Presentation**: All part of Minute Madness Presentation.
  - **Progress Oversight and Communication**: Responsible for overseeing the overall progress of the group, developing ideas in meeting and communication among team members as well as with the tutor.
- **Code and App Design**

  - **Data Structure**: ArrayList, AVLTree.

**D)** **U7587847, Yongsong Xiao** - I have 23% contribution, as follows:

- **Code contribution in the final app**
  - **TestUI** - Class: MainInterfaceTest.java,ResetTest,SignActivityTest、
  - **FoundationTest** - Class: TokenizerTest
 
- **Code and App Design**

  - **Data Structure**: ArrayList.
 




# **Application Description**

The app we designed is a video game library call Sbeam. It provides hundreds of games for users to lookup, and users can also buy it or add it to wishlist. Users can also add other users as friend and start a chat with them.



### Application Case Use

Let’s say Gabe want to buy a new released game and talk about this with his friend:

1. Gabe first needs to sign up for an account and log in using his email. Gabe can also find his password back on the log in page if he forgets his password.

2. After logging in, Gabe can see some recommended game and newly released game in the news tab.

3. If Gabe didn’t find his game here, then he can navigate to the game list tab and then search it. Gabe can use the following similar grammar to search games: “gameName; price > XX; year> XX”.

4. Now Gabe finds the game he wants. He can enter the detail page of that game by clicking on the game. In this page, Gabe can view the detail of game, includes a short description, the publisher of the game, released data, game type and it price.

5. If Gabe doesn’t have enough money to buy it, the he can first add it to his wishlist by click the ADD TO MY WISHLIST button. After adding it to his wishlist, Gabe can navigate to profile tab, and then enter wishlist page to check his game. Now we can see the game Starfield in his wishlist. Gabe can also remove games from his wishlist. 

6. Now Gabe has enough money to buy this game, he can also enter the game detail page by clicking on the game in wishlist. After Gabe bought this game, he can view it on the library page, which can be access in the profile page too.

7. Gabe wants to talk about this game with his friend, he can navigate to friend list page through profile page, and then start a conversation. 

   

# **Application UML**

![img](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/raw/main/items/App_Structure_Diagram.png?inline=false) 

​	(The full image of UML diagram can be found in gitlab repository)

#  **Code Design and Decision**

- **Parser**
  - **Grammar**
    - **Design:** The primary grammar is designed to interpret user input criteria, which consists of year or price constraints or game names. The structure is based on a semi-colon-separated list of tokens, each describing a specific constraint.
    - **Advantages:**
      - **Simplicity:** The grammar is kept simple for ease of use and understanding.
      - **Flexibility:** The design can easily accommodate future extensions or modifications.
      - **Simplicity:** The grammar is kept simple for ease of use and understanding.
      - **User Experience:** The grammar allows users to specify multiple criteria in one input, enhancing search functionality.
    - **Grammar 1 - Year or Price Constraint:** `keyword operator value`
      - **Examples: year=2022, price<50**
    - **Grammar 2 - Name Constraint:** `name`
      - **Example: Super Mario**
    - **Production Rules:**
      - `<Constraint> ::= <YearConstraint> | <PriceConstraint> | <NameConstraint>` (in any order)
      - `<YearConstraint> ::= 'year' <Operator> <Value>`
      - `<PriceConstraint> ::= 'price' <Operator> <Value>`
      - `<NameConstraint> ::= <Name>`
      - `<Operator> ::= '=' | '<' | '>'`
      - `<Value> ::= 'digit'+` // (one or more digits)
      - `<Name> ::= 'letter'+` // (one or more letters)
  - **Tokenizers and Parsers**
    - **Usage**:
        - We use tokenisers in the `Tokenizer` class to split the user's input by `;`.
        - Parsers then identify whether the token is about year, price, or a game name using Java's regex.

    - **Build**:
        - The `StringTokenizer` class divides the input string.
        - Java's regex `Pattern` matches tokens to our grammar.

    - **Advantages**:
        - **Flexibility**: Handles multiple criteria in one input.
        - **Efficiency**: Uses Java's native classes for fast processing.
        - **Precision**: Accurate matches with custom grammar.
        - **Error Handling**: Flags for error detection.

- **Data Structures**

  - **AVL Tree**

    - **Objective:** Used for storing all game objects, where each game object comprises details like game price and release date. This structure facilitated quick searches based on the game price.

    - **Code Locations:** Defined in AVLTree.class.
    - **Reasons:**
      - AVL Tree, being a balanced binary search tree, provides log(n) time complexity for search, insert, and delete operations, which makes it efficient for our purpose.
      - Given that we used the game's price as the key, an AVL tree ensures that searches based on price are faster, allowing users to quickly find games within their budget.
      - The nature of AVL trees automatically balancing themselves means that we avoid worst-case scenarios, ensuring consistent performance.

    - **Use of AVLTree:**
        - insert: insert game data to AVLTree. ([insert](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L95-102))
        - use: If searching conditions contatin "price", filtering by "price" first by using the AVLTree we just created. ([use](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L137))

  - **ArrayList**

    - **Objective:** Adopted for storing chat records in our app. Considering chat messages are inherently sequential and orderly, ArrayList perfectly aligns with this requirement. Furthermore, its compatibility with RecycerView aids in the visualization of chat records.
    - **Code Locations:** Used in chatLogAdapter.java.
    - **Reasons:**
      - ArrayList's dynamic nature is highly efficient for our use case, as chat records can grow indefinitely. The collection adjusts its size accordingly.
      - The integration between ArrayList and `recyclerView` in Android facilitates a seamless experience in displaying chat messages. The data structure's design is inherently suitable for binding with Android's UI components, making the visualization process straightforward and efficient.

- **Design Patterns**
  - **Singleton**: Singleton is used to store the global variables include current user, game list, recommended game and new released game. We choose to use singleton because these variable should be keep same across the whole app, and should be able to be accessed through anywhere in the app.
  - **Observer**: Observer is used to notify all components in this app when there is a data change in firebase database. It is used because we need to keep all the data in the app consistently.
  \- Location: [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L69), [ChatActiviy.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/ChatActivity.java#L73)
  -  **Adapter**: Adapter is used in game list tab, library, wishlist and chat. It fits the object and RecyclerView together so that RecyclerView can display objects in different format.
  \- Location: Basically all classes named with adapter.([SignInAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignInAdapter.java), [LibraryListAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LibraryAndWishlist/LibraryListAdapter.java), [MenuAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/MenuAdapter.java), [NewsGameAdapter.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/News/NewsGameAdapter.java), [GameListAdapter](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListAdapter.java))

 

# **Summary of Known Errors and Bugs**

***\*Bug 1\****: Deleting game data from firebase sometime causes app crash.

***\*Bug 2\****: Logging in again after logging out may cause app crash.



# **Implemented Features**

### Basic Features:

**1.** **[Login]**. Enable user to login using email.

\- Code: [LoginFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/LoginFragment.java)

\- User can login using and unique email and password.

\- This is implemented by using Firebase Auth.

**2.** **[DataFiles]**. DataFiles contains more than 300 games.

\- Each game in DataFiles contains its name, type, year, producer, a short description, picture 	URL and price.

\- This is implemented by using Firebase database.

**3.** **[LoadShowData]**. All game are loaded when enter game list tab.

\- Code: [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java)

\- A list of game will be loaded to the game list RecyclerView in game list tab.

\- This is implemented by reading data from firebase database and load it into the game list	RecyclerView.

**4.** **[Search]**. Enable user to search (filter) for games under different condition

\- Code: [Tokenizer.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Tokenizer.java), [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L111-170) 

\- Users can use the following similar grammar to search games: “gameName; price > XX; year> XX”. User can choose to use compound statement or single condition to search.

\- Use ";" to separate searching conditions.

\- The searching conditions can be in any orders.

\- Tokenizer are used for search features.

\- This is implemented by reading data from game list (AVLTree for search including "price" and Arraylist for others).

 

### Custom Feature:

**Feature Category: Search-related Features**

**1.** **[Search-Invalid]**. On top of giving search results from valid inputs, search functionality can process and
correctly handle partially invalid search queries and give meaningful results.(medium)

\- Code: [Tokenizer.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Tokenizer.java#L32-71), [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L123-130) 
- User can enter invalid search text, the Tokenizer will compare each token with valid patterns, dismiss the invalid token, and prevent bugs or app crash.

**2.** **[Search-Filter]**. Sort and filter a list of items returned from searches, with the use of suitable UI components.(easy)

- Code: [GameListFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameList/GameListFragment.java#L59-84)
- There are a filter button besides the searching textfield. One could filter, sort, and rank their searching result by price/name before or after searching.

**Feature Category: UI Design and Testing**

**3.** **[UI-Layout]**. Incorporate suitable UI layout in the tab for different activities and fragments.(easy)

- Code: All XML layout files.
- This is implemented by modifying the drawable in UI, for example, use a custom item layout for recycler view. Adapt tab view in login UI and main interface by combining tablayout and pagerviewer2.



**Feature Category: Greater Data Usage, Handling and Sophistication**

**4.** **[Data-Profile]**. Create a Profile Page for Users or any Entities.(easy)

- Code: [ProfileFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Profile/ProfileFragment.java).

- User can view it library, wishlist, current balance in the profile fragment. The profile also contain a picture as the user’s profile, user can upload image to update its profile. All the profile data is read from firebase database and then be updated to corresponding widget.



**Feature Category: Firebase Integration**

**5.** **[FB-Auth]**. Use Firebase to implement User Authentication.(easy)

- Code: [LoginFragment.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/LoginFragment.java).

- User login is implemented by using firebase auth, all users’ password and email are stored in fire base.

**6.** **[FB-Persist]**.Use Firebase to persist all data used in your app.(medium)

- All data related to this app is stored in firebase database, including all the game objects, all users’ data and chat logs. The data in app is also updated synchronously as firebase data change, this can be achieved by calling onDataChange() method.



**Feature Category: User Interactivity**

**7.** **[Interact-Micro]** The ability to micro-interact with items/users.(easy)

- Code: [FriendListActivity.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/FriendsListActivity.java), [GameDetail.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/GameDetail.java)

- Allow user to interact with each game object, user can add game to wishlist or buy a game to add it to library. User can also add new friends and chat with them.



**Feature Category: Peer to Peer Messaging**

**8.[P2P-DM]** Provide users with the ability to message each other directly in private. (hard)

- Code: ChatActivity.java, ChatAdapter.java, ChatMessage
- Allow user to chat with another user in real time.

**9.[P2P-Block]** Provide users with the ability to ‘block’ and prevent another user from direct messaging them.(medium)

- Code: FriendsListActivity.java, FriendsListAdapter
- User can add new friends or remove current friends from his friend list.

 

### Voiced Feature

**1.** **[Sign up]** Allow user to sign up an account.(easy)

- Code: [SignUpActivity.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignupFragment.java), [ResetPassword.java](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/ResetPassword.java)

- User can sign up an account using email. User can also reset password by sending an email 	to his email adress.

 **2.[Interact-States]** Users can change their account status, and users can see the status of their friends in the friends list.

- Code: FriendsListActivity.java
- After logging in,the status of user is set to "online", other user can see the status through friends list.

### Testing Summary

*We tested several feactures which contain login、resetPassport、Sign、some Ui controls and Tockenizers. Our tests include using espresso to simulate user operations on the app to implement functions such as login, resetPassport, and Sign, to determine whether the interface entered and the returned results meet user expectations. We also test some Ui controls to ensure that users do what they need to do when using these controls. To ensure that it is smooth and does not crash, the Tokenizer was finally tested and returned the expected value in a variety of different cases to ensure that users can get good feedback and experience when using it.*





1. Tests for Login
   - Code: [SignActivityTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/androidTest/java/com/example/myapplication/SignActivityTest.java) for the [SignActivity Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/SignActivity.java)
   - *Number of test cases: 1*
   - *Code coverage: If user could login successfully*
   - *The test type is Activity. Use espresso to simulate a user using a given password to test whether they can successfully log in to the interface.*

2. Tests for ResetPassword
    - Code: [ResetTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/androidTest/java/com/example/myapplication/ResetTest.java) for the [resetPassport Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/LoginAndSignup/ResetPassword.java)
   - *Number of test cases: 1*
   - *Code coverage: If user could login reset passwords interface successfully*
   - *The test type is Activity. Use espresso to simulate a user login in reset password interface and test if is it right*

...

3. Tests for MainInterface
    - Code: [MainInterfaceTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/androidTest/java/com/example/myapplication/MainInterfaceTest.java) for the [MainInterface Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/MainInterface.java)
   - *Number of test cases: 5*
   - *Code coverage: If every fragments and controls are right in MainInterface*
   - *The test type is Activity. This test mainly uses espresso to test whether the various parts of the main interface are properly designed. Specifically, it tests whether the menu displays three options, and the three options are reasonably positioned and whether they are three options. The menu is slid to ensure that everything goes smoothly. Enter the designated menu.*


4. Tests for Tokenizer
    - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/androidTest/java/com/example/myapplication/TokenizerTest.java) for the [MainInterface Class, entire file](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/app/app/src/main/java/com/example/myapplication/Tokenizer.java)
   - *Number of test cases: 10*
   - *Code coverage: If tokenizer could work well*
   - *This test mainly uses ten different situations to simulate the characters entered by the user to determine whether they match the characters parsed by the tokenizer.*

   




# **Meeting Records**

 

- [Team Meeting 1](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/meeting%201.md)
- [Team Meeting 2](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/meeting%202.md)
- [Team Meeting 3](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/meeting%203.md)
- [Team Meeting 4](https://gitlab.cecs.anu.edu.au/u7618768/ga-23s2/-/blob/main/items/meeting%204.md)

<hr>


# **Conflict Resolution Protocol**

***\*A) Failing to Meet Initial Plan and/or Deadlines:\****

\- First Occurrence: The team member must provide a valid reason for the delay and propose 	a revised deadline. The team will then discuss and agree upon the revised deadline.

\- Repeated Occurrences: The team shall have a meeting to understand the root cause of the 	repeated delays. If necessary, responsibilities may be redistributed. Continued failure may 	lead to escalation to the project supervisor or faculty.

***\*B) Reaching Consensus during Disagreements:\****

\- Open Discussion: Allow each team member to express their viewpoint without 	interruption.

\- Vote: If consensus can't be reached through discussion, a vote shall be taken. Majority will 	decide the outcome.

\- Mediator: If the conflict persists, a neutral third party or team mentor can be brought in to 	mediate the discussion.

***\*C) Member Illness or Unforeseen Absence:\****

\- Immediate Notification: If a team member knows they will be absent, they should notify 	the team as soon as possible.

\- Redistribution of Tasks: The team will meet to discuss the redistribution of tasks to ensure 	that the project remains on track.

\- Contingency Plan: Each team member should have a secondary person familiar with their 	tasks to ensure a smoother transition of duties in case of sudden absences.

***\*D) Mitigation of Unforeseen Incidents:\****

\- Regular Check-ins: Hold brief daily or bi-weekly check-ins to update the team on progress 	and address any potential issues.

\- Backup Plans: Always have a "Plan B" for critical project components. This might involve 	having backup resources, alternative methods, or secondary timelines.

\- Documentation: Ensure all work is well-documented. This will aid any team member in 	picking up where another left off, if needed.
