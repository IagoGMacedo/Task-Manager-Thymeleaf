function formatarDataHora(input) {
    var partes = input.split(' ');
    if(partes.length === 1)
      return input + ' 00:00:00'
    // verifica o formato da data
    if (partes.length === 2) {
      var data = partes[0];
      var hora = partes[1];

      // completa a hora com zeros 
      var horaPartes = hora.split(':');
      horaPartes[0] = horaPartes[0].padStart(2, '0');
      horaPartes[1] = horaPartes[1] ? horaPartes[1].padStart(2, '0') : '00';
      horaPartes[2] = horaPartes[2] ? horaPartes[2].padStart(2, '0') : '00';

      // formata a data e hora de volta
      return data + ' ' + horaPartes.join(':');
    }

    // retorna a entrada sem alterações se não houver formato esperado
    return input;
    
  }


  function validarFormulario() {
    var nomeTarefa = document.getElementsByName("name")[0].value;
    var dataExecucao = document.getElementsByName("date")[0].value;

    console.log("nomeTarefa: "+nomeTarefa.trim());
    console.log("dataExecucao: "+dataExecucao.trim());
    
    // campos vazios
    if (nomeTarefa.trim() === "" || dataExecucao.trim() === "") {
      alert("Preencha todos os campos");
      return false; 
    }

    if(dataExecucao.split(' ').length > 2){
      alert("A data pode ser preenchida no máximo até o formato DD/mm/dd hh:mm:ss, a parte de hora não é obrigatória");
      return false;
    }

    document.getElementsByName("date")[0].value = formatarDataHora(dataExecucao.trim());
    return true;
  }