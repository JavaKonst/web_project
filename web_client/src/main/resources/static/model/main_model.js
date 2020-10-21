function getDistrictsWithAvgSalary() {
    document.getElementById("tableList").innerHTML = "";
    showField("edit_employer", false);
    showField("edit_district", false);
    showField("create_district", false);
    showField("create_employer", false);
    showField("search_employer", false);

    document.getElementById("new_district_name").value = "";

    let url = get_host_service() + "/all_d_with_s";
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let districts = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                            '<th>Название отдела</th>\n' +
                            '<th>Средняя зарплата</th>\n' +
                            '<th></th>\n' +
                            '<th></th>\n' +
                            '<th></th>\n' +
                       '</tr>';
            for (let i = 0; i < districts.length; i++) {
                let district = districts[i];
                 html = html +
                    '<tr> '+
                       '<td>' + district[0] + '</td>\n' +
                       '<td>' + district[1] + '</td>\n' +
                       '<td><button onclick="getEmployersByDistrict(\'' + district[0] + '\')">Список сотрудников</button></td>' +
                       '<td><button onclick="deleteDistrict(\'' + district[0] + '\')">Удалить отдел</button></td>' +
                       '<td><button onclick="showEditDistrict(\'' + district[0] + '\')">Редактировать</button></td>' +
                    '</tr>';
            }
            document.getElementById("tableList").innerHTML = html;
        }
    };
    send_get(output, url);
}

function getEmployersByDistrict(district_name){
    showField("search_employer", true);
    showField("create_employer", true);
    showField("create_district", false);

    let url = get_host_service() + "/all_e_by_district?name="+district_name;
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let employers = JSON.parse(this.responseText);
            createTableEmployers(employers);
            getListDistricts();
        }
    };
    send_get(output, url);
}

function findEmployer() {
    let date0 = document.getElementById("date0_employer").value;
    let date1 = document.getElementById("date1_employer").value;
    if(date1 == "") {
        date1=date0;
        document.getElementById("date1_employer").value = date0;
    }

    if(date0 == "" && date1 != "") {
            date0=date1;
            document.getElementById("date0_employer").value = date1;
        }

    let url = get_host_service() + "/search?ds=" + date0 + "&de="+date1;
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let employers = JSON.parse(this.responseText);
            createTableEmployers(employers);
        }
    }
    send_get(output, url);
}

function createEmployer() {
    let employerName = document.getElementById("employer_name");
    let employerBirthday = document.getElementById("employer_birthday");
    let employerSalary = document.getElementById("employer_salary");
    let employerDistrict = document.getElementById("employer_district");

    if(employerName.value=="" || employerBirthday.value=="" || employerSalary.value=="" || employerDistrict.options.selectedIndex==0) {
        alert("Заполните все поля");
        return;
    }

    let url = get_host_service() + "/e_create";
    let new_employer = JSON.stringify({
        name: employerName.value,
        birthday: employerBirthday.value,
        salary: employerSalary.value,
        district: employerDistrict.value
        });

    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            employerName.value = "";
            employerBirthday.value = "";
            employerSalary.value = "";
            employerDistrict.value = "";

            loadEmployers();
        }
    }

    send_post(output, url, new_employer);
}

function createDistrict() {
    let districtName = document.getElementById("new_district_name");
    if(districtName.value=="") {
        alert("Заполните все поля");
        return;
    }

    let url = get_host_service() + "/d_create";
    let new_district = JSON.stringify({
        name: districtName.value,
        });

    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            districtName.value = "";
            loadDistricts();
        }
    }

    send_post(output, url, new_district);
}

function editEmployer() {
    let employerId = document.getElementById("employer_id");
    let employerName = document.getElementById("edit_employer_name");
    let employerBirthday = document.getElementById("edit_employer_birthday");
    let employerSalary = document.getElementById("edit_employer_salary");
    let employerDistrict = document.getElementById("edit_employer_district");

    if(employerName.value=="" || employerBirthday.value=="" || employerSalary.value=="" || employerDistrict.options.selectedIndex==0) {
        alert("Заполните все поля");
        return;
    }

    let url = get_host_service() + "/e_update";
    let new_employer = JSON.stringify({
        id: employerId.value,
        name: employerName.value,
        birthday: employerBirthday.value,
        salary: employerSalary.value,
        district: employerDistrict.value
        });

    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            employerId.value = "";
            employerName.value = "";
            employerBirthday.value = "";
            employerSalary.value = "";
            employerDistrict.value = "";

            showField("edit_employer", false);
            loadEmployers();
        }
    }

    send_post(output, url, new_employer);
}

function editDistrict(){
    let districtId = document.getElementById("district_id");
    let districtName = document.getElementById("edit_district_name");

    if(districtName.value=="") {
        alert("Заполните все поля");
        return;
    }

    let url = get_host_service() + "/d_update";
    let new_district = JSON.stringify({
        id: districtId.value,
        name: districtName.value,
        });

    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            showField("edit_employer", false);
            districtId.value = "";
            districtName.value = "";
            loadDistricts();
        }
    }

    send_post(output, url, new_district);
}

function getListDistricts(){
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let districts = JSON.parse(this.responseText);
            let html = '<option>Выберите отдел</option>';
            for (let i = 0; i < districts.length; i++) {
                let district = districts[i];
                html = html + "<option>"+district.name + "</option>"
            }
        document.getElementById("employer_district").innerHTML = html;
        document.getElementById("edit_employer_district").innerHTML = html;
        }
    };

    let url = get_host_service() + "/all_d";
    send_get(output, url);
}

function loadDistricts() {
    document.getElementById("tableList").innerHTML = "";
    showField("edit_employer", false);
    showField("edit_district", false);
    showField("create_district", true);
    showField("create_employer", false);
    showField("search_employer", false);
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let districts = JSON.parse(this.responseText);
            createTableDistricts(districts);
        }
    };

    let url = get_host_service() + "/all_d";
    send_get(output, url);
}

function loadEmployers() {
    document.getElementById("tableList").innerHTML = "";
    showField("edit_employer", false);
    showField("edit_district", false);
    showField("create_district", false);
    showField("create_employer", true);
    showField("search_employer", true);

    let date0 = document.getElementById("date0_employer");
    let date1 = document.getElementById("date1_employer");
    if (date0 != null) date0.value = "";
    if (date1 != null) date1.value = "";

    let output = function() {
        if (this.readyState == 4 && this.status == 200) {
            let employers = JSON.parse(this.responseText);
            createTableEmployers(employers);
        }
    };
    let url = get_host_service() + "/all_e";
    send_get(output, url);
    getListDistricts();
}

function deleteEmployer(employer_id) {
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            loadEmployers();
        }
    };
    let url = get_host_service() + "/e_delete?id=" + employer_id;
    send_delete(output, url);
}

function deleteDistrict(district_name) {
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            loadDistricts();
        }
    };
    let url = get_host_service() + "/d_delete?name=" + district_name;
    send_delete(output, url);
}

function createTableEmployers(employers){
    let html =
        '<tr>\n' +
            '<th>ФИО</th>\n' +
            '<th>Дата рождения</th>\n' +
            '<th>Зарплата</th>\n' +
            '<th>Отдел</th>\n' +
            '<th>Удалить</th>\n' +
            '<th>Изменить</th>\n' +
        '</tr>';
    for (let i = 0; i < employers.length; i++) {
        let employer = employers[i];
        html = html +
            '<tr> '+
                '<td>' + employer.name + '</td>\n' +
                '<td>' + employer.birthday + '</td>\n' +
                '<td>' + employer.salary + '</td>\n' +
                '<td>' + employer.district.name + '</td>' +
                '<td><button onclick="deleteEmployer(' + employer.id + ')">Удалить</button></td>' +
                '<td><button onclick="showEditEmployer(' + employer.id + ')">Редактировать</button></td>' +
            '</tr>';
    }
    document.getElementById("tableList").innerHTML = html;
}

function createTableDistricts(districts){
    let html =
        '<tr>\n' +
            '<th>Название отдела</th>\n' +
            '<th>Количество сотрудников</th>\n' +
            '<th></th>\n' +
            '<th></th>\n' +
            '<th></th>\n' +
        '</tr>';
    for (let i = 0; i < districts.length; i++) {
        let district = districts[i];
        html = html +
            '<tr> '+
                '<td>' + district.name + '</td>\n' +
                '<td><button onclick="getEmployersByDistrict(\'' + district.name + '\')">Список сотрудников</button></td>' +
                '<td><button onclick="deleteDistrict(\'' + district.name + '\')">Удалить отдел</button></td>' +
                '<td><button onclick="showEditDistrict(\'' + district.name + '\')">Редактировать</button></td>' +
            '</tr>';
    }
    document.getElementById("tableList").innerHTML = html;
}

function showEditEmployer(employer_id){
    let output = function() {
        if (this.readyState == 4 && this.status == 200) {
            getListDistricts();
            let employer = JSON.parse(this.responseText);
            document.getElementById("employer_id").value = employer.id;
            document.getElementById("edit_employer_name").value = employer.name;
            let birthday = employer.birthday.split(".");
            document.getElementById("edit_employer_birthday").value = birthday[2]+"-"+birthday[1]+"-"+birthday[0];
            document.getElementById("edit_employer_salary").value = employer.salary;
            document.getElementById("edit_employer_district").value = employer.district.name;
            showField("edit_employer", true);
        }
    };
    let url = get_host_service() + "/get_e_by_id?id="+employer_id;
    send_get(output, url);
}

function showEditDistrict(districtName){
    document.getElementById("edit_district_name").value = districtName;

    let url = get_host_service() + "/get_d_by_name?name=" + districtName;
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let district = JSON.parse(this.responseText);
            document.getElementById("district_id").value = district.id;
            showField("edit_district", true);
        }
    }

    send_get(output, url);
}

function showField(field_name, is_show){
    let f = "none";
    if(is_show) f = "block";
    document.getElementById(field_name).style.display = f;
}
