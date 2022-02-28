function transform(message, out){
	var newMessage = GenericMessage.create(false);
	var srcObj = JSON.parse(message);
	newMessage.addProperty("id",srcObj.id);
	newMessage.addProperty("entityType",srcObj.type);
	if(srcObj.txId)
		newMessage.addProperty("srcTxId",srcObj.txId);
	newMessage.operation = "updateEntity";
	if(srcObj.op == "C")
		newMessage.operation = "createEntity";
	else if(srcObj.op == "U")
		newMessage.operation = "updateEntity";
	else if(srcObj.op == "D")
		newMessage.operation = "deleteEntity";
	newMessage.source = _config.name;
	srcObj.op = undefined;
	srcObj.txId = undefined;
	srcObj.entityType = undefined;
	srcObj.sequence = undefined;
	newMessage.bodyAsString = JSON.stringify(srcObj);
	//print("src msg:" + newMessage.bodyAsString);
	out.collect(newMessage);
}