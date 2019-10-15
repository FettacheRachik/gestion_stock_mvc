$(document).ready(function (){
	$("#tauxTva").on("keyup",function (){
		tvaKeyUpFunction();
	})
})


tvaKeyUpFunction = function (){
	var prixUnitHT=parseFloat($("#prixUnitHT").val());
	var tauxTva= parseFloat($("#tauxTva").val());
	
	
	var prixUnitTTC= parseFloat((prixUnitHT*(tauxTva/100))+ prixUnitHT);
	
	$("#prixUnitTTC").val(prixUnitTTC);

	var value= $("#prixUnitTTC").val();
	console.log("pru Unit ttc" + value);
	
}
