## Fetch-Coding-Challenge  
Basic setup
1) You need to have the latest version of java so go here:  
    - https://www.oracle.com/java/technologies/downloads
2) I used Postman to send POST and GET requests to the websevice. Link here: 
    - https://www.postman.com/
    - You just need to create an account, then you can uses it in the browser.
3) To run the program just double click:  **runtransaction.bat**  in the main directory

Postman
1) To get started with Postman head over to Workspaces on the top left
2) then click on the plus
![big plus](https://user-images.githubusercontent.com/36714045/134416740-e4029a0d-2981-4ecf-9a9d-a37a37551edb.PNG)
3)Below the url box select **Body**, **raw**, and **JSON**
![bodyjson](https://user-images.githubusercontent.com/36714045/134413141-735d94ea-2b9e-4694-a131-636e8966a6d4.PNG)

Sending Requests
1) Add transaction route
    - Set it to **POST** 
    - paste this url: http://localhost:8080/addtransaction?
    - paste the transaction into the body and hit send
        - exmaple: { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
2) Spend Points route
    - Set it to **POST** 
    - paste this url: http://localhost:8080/spendpoints?
    - paste the the points into the body and hit send
        - example: { "points": 5000 }
3) View balance route
    - Set it to **Get** 
    - paste this url: http://localhost:8080/balance?

 

