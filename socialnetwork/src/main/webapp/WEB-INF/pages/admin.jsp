
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="main-table  container">
    <div class="search">
        <form action="<c:url value="/stats/"/>" class="form-search">
            <input name="month" type="month"  />
            <button type="submit"><i class="fas fa-search"></i></button>
        </form>
    </div>

    <canvas id="myChart"></canvas>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

<script>

    let data = [];
    let labels = [];

    <c:forEach items="${stat}" var="y">
    labels.push("Tháng" + '${y[0]}');
    data.push('${y[1]}');
    </c:forEach>

    for (let i = 1; i <= 12; i++) {
        if (labels[i]!==i && data[i]===null) {
            labels.push("Tháng " + i);
            data.push(0);
        }
    }
// Thêm giá trị 0 cho các tháng còn thiếu
    for (let i = 1; i <= 12; i++) {
        if (!labels.includes("Tháng" + i)) {
            labels.push("Tháng " + i);
            data.push(0);
        }
    }
    var chartData = data;

    // Create a chart using Chart.js
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar', // Specify chart type (e.g., bar, line, pie)
        data: {
            labels,
            datasets: [{
                    label: 'My Chart',
                    data: data,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)', // Customize colors
                    borderColor: 'rgba(75, 192, 192, 1)', // Customize colors
                    borderWidth: 1
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });


    // Lấy tham số truy vấn từ URL
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

// Lấy giá trị tháng từ tham số truy vấn
    const monthParam = urlParams.get("month");

    if (monthParam) {
        // Tách tháng từ chuỗi tham số truy vấn
        const parts = monthParam.split("-");
        if (parts.length === 2) {
            const month = parts[1];
            const  year = parts[0];

            // Tạo một tham số truy vấn mới với giá trị tháng
            const newQueryString = "?month=" + month + "&year=" + year;
            const newUrl = window.location.pathname + newQueryString;

            // Chuyển hướng đến URL mới với tham số truy vấn mới
            window.location.href = newUrl;
        }
    }

</script>

<!--<style>
    #myChart{
        width: 800px !important;
    }
</style>-->
