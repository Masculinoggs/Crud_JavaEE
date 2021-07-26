/**
 *Confirmação de exlusão de um contato 
	@author Gilvan Nascimento
*/
function confirmar(iduse) {
	let resposta = confirm("Confirma a exclusão deste contato?")
	if (resposta === true) {
		window.location.href = "delete?iduse=" + iduse
	}
}