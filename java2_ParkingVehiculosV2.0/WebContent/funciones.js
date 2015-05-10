/**
 * 
 */
expr = /^[0-9]{4}[A-Za-z]{3}$/;

function comprobarMatricula(){
	var matricula = document.getElementById('matricula').value;
	if(!expr.test(matricula)){
		alert("Matrícula mal insertada!!!");
		return false;
	}else{
		return true;
	}
}

function comprobar(){
	var matricula = document.getElementById('matricula').value;
	var numRuedas = document.getElementById('numRuedas').value;
	var consumo = document.getElementById('consumo').value;
	var marca = document.getElementById('marca').value;
	var color = document.getElementById('color').value;
	
	if(!expr.test(matricula)){
		alert("Matrícula mal insertada!!!");
		return false;
	}else if(numRuedas.length == 0 || isNaN(numRuedas)){
		alert("Número de ruedas debe ser un número!!!");
		return false;
	}else if(consumo.length == 0 || isNaN(consumo)){
		alert("Consumo debe ser un número!!!");
		return false;
	}else if(marca.length == 0){
		alert("Falta la marca del vehículo!!!");
		return false;
	}else if(color.length == 0){
		alert("Falta color del vehículo!!!");
		return false;
	}else{
		alert("Felicidades!!!\nHas guardado un vehículo nuevo.");
		return true;
	}
}