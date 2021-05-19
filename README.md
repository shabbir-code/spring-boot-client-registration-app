## Sample Json Request

{
 
 "firstName":"Mohammed",
 "lastName":"Shabbir",
 "mobileNumber":9831039938,
 "id":9202204720082,
 "address":"INDIA"
 
    
}


{
   "firstName":"Amit",
   "lastName":"Kumar",
   "mobileNumber":8939705974,
   "id":1302204720083,
   "address":"INDIA"
}


// id is same exception is thrown

{
   "firstName":"Nam",
   "lastName":"Tariang",
   "mobileNumber":8989100000,
   "id":9202204720082,
   "address":"INDIA"
}

//mobile number is same exception is thrown


{
   "firstName":"Vinod",
   "lastName":"Kumar",
   "mobileNumber":9831039938,
   "id":2202204720085,
   "address":"INDIA"
}


//update request with updated name 

{
 
 "firstName":"Md",
 "lastName":"Sha",
 "mobileNumber":9831039938,
 "id":9202204720082,
 "address":"INDIA"
 
    
}


//update request with Amit's number

{
 
 "firstName":"Md",
 "lastName":"Sha",
 "mobileNumber":8939705974,
 "id":9202204720082,
 "address":"INDIA"
 
    
}


http://localhost:9090/search-client?mobileNumber=9831039938

http://localhost:9090/search-client?id=9202204720082

http://localhost:9090/search-client?firstName=Mohammed

