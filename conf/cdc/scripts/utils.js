function stringify (obj, keys, include) {
  if (Array.isArray(obj)) {
    var stringifiedArr = [];
    for (var i = 0; i < obj.length; i++) {
      stringifiedArr[i] = stringify(obj[i]);
    }
    return JSON.stringify(stringifiedArr);
  } else if (typeof obj === 'object' && obj !== null) {
    var acc = [];
    var sortedKeys = Object.keys(obj).sort();
    for (var i = 0; i < sortedKeys.length; i++) {
      var k = sortedKeys[i];
	  if(keys){
		  var index = keys.indexof(k);
		  if(include){
			  if(index != -1)
				  acc.push(k + ":" + stringify(obj[k]));
		  }else{
			  if(index == -1)
				  acc.push(k + ":" + stringify(obj[k]));
		  }
	  }else{
		acc[i] = k + ":" + stringify(obj[k]);
	  }
    }
    return acc.join('|');
  }
  return obj;
}

function digest_md5 (obj) {
  var hash = CryptoJS.MD5(stringify(obj));
  return hash.toString(CryptoJS.enc.Base64);
}

function digest_murmur (obj,keys,include) {
  return murmurHash3.x64.hash128(stringify(obj,keys,include));
}

function isHashEquals(input,state,keys,include){
	//var hash = digest_md5(input);
	var hash = digest_murmur(input,keys,include);
	if(hash == state.hash) {
		return true;
	}else{
		state.hash = hash;
		return false;
	}
}