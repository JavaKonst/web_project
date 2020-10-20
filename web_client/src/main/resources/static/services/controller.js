function send_get(input_function, url){
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = input_function;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function send_delete(input_function, url){
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = input_function;
    xmlhttp.open("DELETE", url, true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    xmlhttp.send();
}

function send_post(input_function, url, new_employer){
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = input_function;
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    xmlhttp.send(new_employer);
}



