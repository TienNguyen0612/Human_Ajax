let index = 0;

function addNewHuman() {
    //lấy dữ liệu
    let name = $('#name').val();
    let phone = $('#phone').val();
    let address = $('#address').val();
    let idCard = $('#idCard').val();
    let newHuman = {
        name: name,
        phone: phone,
        address: address,
        idCard: idCard
    };

    //gọi ajax
    $.ajax({
       headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       },
        type: "POST",
        data: JSON.stringify(newHuman),
        //tên API
        url: "http://localhost:8080/api/humans",
        //xử lý khi thành công
        success: function () {
            getHuman();
        }

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function editHuman(id) {
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/humans/${id}`,
        //xử lý khi thành công
        success: function (data) {
            $('#name').val(data.name);
            $('#phone').val(data.phone);
            $('#address').val(data.address);
            $('#idCard').val(data.idCard);
            index = data.id;
            document.getElementById("form").hidden = false;
            document.getElementById("form-button").onclick = function () {
                editHuman1()
            };
        }
    });
}

function editHuman1() {
    //lấy dữ liệu
    let name = $('#name').val();
    let phone = $('#phone').val();
    let address = $('#address').val();
    let idCard = $('#idCard').val();
    let newHuman = {
        name: name,
        phone: phone,
        address: address,
        idCard: idCard
    };

    //gọi ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(newHuman),
        //tên API
        url: `http://localhost:8080/api/humans/${index}`,
        //xử lý khi thành công
        success: function () {
            getHuman()
        }
    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function getHuman() {
    $.ajax({
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/humans",
        //xử lý khi thành công
        success: function (data) {
            // hiển thị danh sách ở đây
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th>Phone</th>\n' +
                '<th>Address</th>\n' +
                '<th>IdCard</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayHuman(data[i]);
            }
            document.getElementById("humanList").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function deleteHuman(id) {
    if(confirm('Are you sure you want to Delete?') === true) {
        $.ajax({
            type: "DELETE",
            //tên API
            url: `http://localhost:8080/api/humans/${id}`,
            //xử lý khi thành công
            success: function () {
                getHuman();
            }
        });
    }
}

function searchHuman() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        //tên API
        url: `http://localhost:8080/api/humans/search?search=${search}`,
        //xử lý khi thành công
        success: function (data) {
            // hien thi danh sach o day
            let content = '<tr>\n' +
                '<th>Name</th>\n' +
                '<th>Phone</th>\n' +
                '<th>Address</th>\n' +
                '<th>IdCard</th>\n' +
                '<th colspan="2">Action</th>\n' +
                '</tr>';
            for (let i = 0; i < data.length; i++) {
                content += displayHuman(data[i]);
            }
            document.getElementById('humanList').innerHTML = content;
            document.getElementById("search-form").reset()
        }
    });
    event.preventDefault();
}

function displayHuman(human) {
    return `<tr><td>${human.name}</td><td>${human.phone}</td><td>${human.address}</td><td>${human.idCard}</td>
                    <td><button class="btn btn-danger" onclick="deleteHuman(${human.id})"><i class="fa-solid fa-trash"></i></button></td>
                    <td><button class="btn btn-warning" onclick="editHuman(${human.id})"><i class="fa-solid fa-pencil"></i></button></td>`;
}

function displayFormCreate() {
    document.getElementById("form").reset()
    document.getElementById("form").hidden = false;
    document.getElementById("form-button").onclick = function () {
        addNewHuman();
    }
}

getHuman()