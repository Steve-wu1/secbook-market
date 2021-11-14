numberOfMan = 0;
numberOfWomen = 0;
numberOfDeliveryman = 0;
numberOfCook = 0;
numberOfWaiter = 0;
var dataCountMap = [];
var data1 = [];
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/data",
        type: "GET",
        dataType: "json",
        header: {
            'Access-Control-Allow-Origin': "http://localhost:8080"
        },
        success: function (data) {
            prepareData(data)
        },
        async: false
    })
    displaySuggestion();

    function prepareData(DataList) {
        for (i = 0; i < DataList.length; i++) {
            dataCountMap.set('data' + DataList[i].id, 0);
            data1.push(DataList[i]);
        }
    }

    function displaySuggestion() {
        var curData = data1;
        numberOfMan = curData[0].numberOfMan;
        numberOfWomen = curData[0].numberOfWomen;
        numberOfDeliveryman = curData[0].numberOfDeliveryman;
        numberOfCook = curData[0].numberOfCook;
        numberOfWaiter = curData[0].numberOfWaiter;
    }
})
