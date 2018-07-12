# android-listview


ListView

Adapter manages the data in ListView.

Default Adapter
    Array Adapter
    Cursor Adapter
    
Avoiding layout inflation and object creation

If Android determines that a row is not visible anymore, 
it allows the getView() of the adapter method to reuse the associated 
view via the convertView parameter.
    
ViewHolder  
-----------  

A ViewHolder class is typically a static inner class in 
your adapter which holds references to the relevant views in your layout. 
This reference is assigned to the row view as a tag via the setTag() method.    

Volley Library
===============

Volley is a networking library for Android that manages network requests. 
It bundles the most important features you’ll need, such as accessing JSON APIs,
 loading images and String requests in an easier-to-use package.
 
 Request Queue
 --------------
 
 Working with Volley is handled by creating a RequestQueue 
 and passing it Request objects. The RequestQueue manages worker threads for running the network operations,
  reading from and writing to the cache, and parsing responses.
  
  Volley offers a variety of features, as mentioned in its documentation:
  
  Automatic scheduling of network requests.
  Multiple concurrent network connections.
  Caching.
  Request prioritization.
  Cancellation of ongoing API requests.
  
  JsonObjectRequest request 
  JsonObjectRequest(RequestMethod, URL, null,  new ResponseListener(), new ErrorListener());
  
  RequestMethod (GET, POST, PUT, DELETE, etc.)
  URL: String of the URL of the required object
  JSONObject: An optional object posted with the request, null if there is no object posted
  ResponseListener: Response Listener, whose callback method will contain the response
  ErrorListener: A Response.ErrorListener whose callback method will contain any problem with the request.
  
   ImageLoader provides an in-memory cache to sit in front of the normal Volley cache
   
   NetworkImageView is an efficient way to replace ImageView when images are being loaded from the network
   
  

    
    
    
