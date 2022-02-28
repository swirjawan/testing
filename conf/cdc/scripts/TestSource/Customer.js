{

targets: {

  "cust" : {
		type: "Customer"
 }
},

rels: {
	
	"table1_cust" : {
		"to": "TestSource.table1",
        "cardinality": "one",
        "direction": "in",
        transform: function(input,output,state,operation){
			output.cust = input;
		}
	}
	
	/*,

    "addr_cust" : {
		"to": "Address",
        "cardinality": "many",
		"field": "addr",
		"direction": "in"
		//"direction": "out",
        //"path": "table1_cust.table2_table1.table2_addr"
	}*/
}

}