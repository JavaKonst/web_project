function getDistrictsWithAvgSalary() {
        let url = "/web_service-1.0/all_d_with_s";
        let output = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("input_form").style.display = "none";
                let districts = JSON.parse(this.responseText);
                let html = '<tr>\n' +
                                '<th>Название отдела</th>\n' +
                                '<th>Средняя зарплата</th>\n' +
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
                        '</tr>';

                }
                document.getElementById("tableList").innerHTML = html;

            }
        };
        send_get(output, url);
    }

function getEmployersByDistrict(district_name){
    let url = "/web_service-1.0/all_e_by_district?name="+district_name;
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("input_form").style.display = "block";
            let employers = JSON.parse(this.responseText);
            createTable(employers);
            loadDistricts();
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

    let url = "/web_service-1.0/search?ds=" + date0 + "&de="+date1;
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let employers = JSON.parse(this.responseText);
            createTable(employers);
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

    let url = "/web_service-1.0/e_create";
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

function loadDistricts() {
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            let districts = JSON.parse(this.responseText);
            let html = '<option>Выберите отдел</option>';
            for (let i = 0; i < districts.length; i++) {
                let district = districts[i];
                html = html + '<option>'+district.name + '</option>'
            }
            document.getElementById("employer_district").innerHTML = html;
        }
    };

    let url = "/web_service-1.0/all_d";
    send_get(output, url);
    }

function loadEmployers() {
        let date0 = document.getElementById("date0_employer");
        let date1 = document.getElementById("date1_employer");
        if (date0 != null) date0.value = "";
        if (date1 != null) date1.value = "";

        let output = function() {
            if (this.readyState == 4 && this.status == 200) {
                let employers = JSON.parse(this.responseText);
                document.getElementById("input_form").style.display = "block";
                createTable(employers);
            }
        };
        let url = "/web_service-1.0/all_e";
        send_get(output, url);
        loadDistricts();
    }

function createTable(employers){
    let html =
        '<tr>\n' +
            '<th>ФИО</th>\n' +
            '<th>Дата рождения</th>\n' +
            '<th>Зарплата</th>\n' +
            '<th>Отдел</th>\n' +
            '<th>Удалить</th>\n' +
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
            '</tr>';

    }
    document.getElementById("tableList").innerHTML = html;
}

function deleteEmployer(employer_id) {
        let output = function () {
            if (this.readyState == 4 && this.status == 200) {
                loadEmployers();
            }
        };
        let url = "/web_service-1.0/e_delete?id=" + employer_id;
        send_delete(output, url);
    }

function deleteDistrict(district_name) {
    let output = function () {
        if (this.readyState == 4 && this.status == 200) {
            getDistrictsWithAvgSalary();
        }
    };
    let url = "/web_service-1.0/d_delete?name=" + district_name;
    send_delete(output, url);
}

// loadEmployers();