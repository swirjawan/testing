{
	
filter: function(input,state){
		return isHashEquals(input,state);
 },	

transform: function(input,output,state,operation){
		var cust = {};
		cust.fname = input.fname;
		cust.lname = input.lname;
		output.table1_cust = cust;
   },
   
   
rels: {

	/*"table2_table1" : {
		"to": "table2",
		"field" : "addrs",
		"cardinality": "many",
		"direction": "in"
	},*/

	"table1_cust" : {
		"to": "Customer",
		"cardinality": "one",
		"direction": "out",
		"id": function(input,state){
			if(input && input.id)
				return "Customer-"+ input.id;
			return null;
		}
	}
}

}