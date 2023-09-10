function deleteProduct(path) {
    console.log(path);
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {method: "delete"}).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
}


function addCart(productId) {
    fetch("/trading-platform/cart/" + productId).then(res => res.json()).then(data => {
        var d = document.getElementById("cart")
        if (d != null)
            d.innerText = data;
    })
}

function deleteCart(path,id) {
    let c = document.getElementById(`cart` + id)
    console.log(c)
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(response => {
            if (response.status === 204) {
                c.style.display = "none";
                location.reload();
            } else {
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
            }
        }).catch(error => {
            console.error('Error:', error);
        });

//            let d = document.getElementsByClassName("cart-counter")
//            for (let i = 0; i < d.length; i++)
//                d[i].innerText = data.total_quantity
//
//            let d2 = document.getElementsByClassName("cart-amount")
//            for (let i = 0; i < d2.length; i++)
//                d2[i].innerText = data.total_amount.toLocaleString("en-US")



    }

}


function request(path) {
    console.log(path);
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {method: "post"}).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
}
