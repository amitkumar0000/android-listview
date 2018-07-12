# android-listview


ListView

Adapter manages the data in ListView.

Default Adapter
    Array Adapter
    Cursor Adapter
    
Avoiding layout inflation and object creation

If Android determines that a row is not visible anymore, it allows the getView() of the adapter method to reuse the associated view via the convertView parameter.
    
ViewHolder    

A ViewHolder class is typically a static inner class in your adapter which holds references to the relevant views in your layout. This reference is assigned to the row view as a tag via the setTag() method.    
    
    
    
