{

targets: {

  "addr" : {
		type: "Address"
 }

},
   
rels: {
	
	"table2_addr" : {
		"to": "TestSource.table2",
        "cardinality": "one",
		"direction": "in",

        transform: function(input,output,state,operation){
			if(state.count)
				state.count ++;
			else
				state.count = 1;
			input.count = state.count;
			output.addr = input;
		}
	},

    "addr_cust" : {
		"to": "Customer",
        "cardinality": "one",
		"field": "cust",
		"direction": "out",
		"id" : function(input,state){
			if(input && input.cust_id){
				return "Customer-" + input.cust_id;
			}
			return null;
		}
        //"direction": "in",
        //"path": "table2_addr.table2_table1.table1_cust"
	}
}

}