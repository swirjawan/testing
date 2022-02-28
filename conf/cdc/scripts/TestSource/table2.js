{

transform: function(input,output,state,operation){
		var addr = {};
		addr.line1 = input.line1;
		addr.line2 = input.line2;
		addr.cust_id = input.cust_id;
		output.table2_addr = addr;
        state.count = 1;
        input.count = 100;
        addr.count = "200";
   },
   
rels: {
	
	"table2_table1" : {
		"to": "table1",
        "cardinality" : "one",
		"field" : "cust",
		"direction": "out",
		"id": function(input,state){
			if(input.cust_id)
				return input.cust_id;
			return null;
		}
	
	},

    "table2_addr" : {
		"to": "Address",
		"cardinality": "one",
        "direction": "out",
		"id": function(input,state){
			if(input && input.id)
				return "Address-"+ input.id;
			return null;
		}
	}
	
}

}