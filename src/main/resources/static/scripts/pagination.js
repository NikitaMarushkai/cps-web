var rows_per_page = 10;
var total_rows;
var str_year;
var str_location;
var str_engine_hours;
var str_separator_hours;
var str_price;
var str_on_request;
var str_instructions;
var filterType = "";

function initLangVars() {
    //Init multilingual variables
    str_year = $('#year').text();
    str_location = $('#location').text();
    str_engine_hours = $('#engine_hours').text();
    str_separator_hours = $('#separator_hours').text();
    str_price = $('#price').text();
    str_on_request = $('#on_request').text();
    str_instructions = $('#instructions').text();
}

function initPageNumbers() {
    //get total rows number
    $.get('/rest/totalRows?filter=' + filterType, function (data) {
        setLocation('#0');
        total_rows = parseInt(data);

        $('.page-numbers').html('');

        $('.page-numbers').append('<li class="page-list" style="display: inline;" id="first"><a href="#0" onclick="getPage(0,0);">' +
            '<span class="glyphicon glyphicon-fast-backward"></span></a></li>');
        $('.page-numbers').append('<li class="page-list" style="display: inline;" id="previous"><a onclick="getNextPage(false, 0);">' +
            '<span class="glyphicon glyphicon-chevron-left"></span></a></li>');

        //Loop through every available page and output a page link
        var count = 0;
        var pid = 0;
        for (var x = 0; x < total_rows; x += rows_per_page) {
            if (pid < 6) {
                $('.page-numbers').append('<li class="page-list listing pid' + pid + '" style="display: inline;"><a href="#' + count + '" onclick="getPage(' + count + ',' +
                    pid + ');">' + parseInt(count + 1) + '</a></li>');
            } else {
                $('.page-numbers').append('<li class="page-list listing pid' + pid + '" style="display: none;"><a href="#' + count + '" onclick="getPage(' + count + ',' +
                    pid + ');">' + parseInt(count + 1) + '</a></li>');
            }
            count++;
            pid++;
        }
        $('.page-numbers').append('<li class="page-list" style="display: inline;" id="next"><a onclick="getNextPage(true,' + parseInt(count - 1) + ');">' +
            '<span class="glyphicon glyphicon-chevron-right"></span></a></li>');
        $('.page-numbers').append('<li class="page-list" style="display: inline;" id=""><a href="#' + parseInt(count - 1) + '" onclick="getPage(' + parseInt(count - 1) + ',' +
            parseInt(pid - 1) + ');"><span class="glyphicon glyphicon-fast-forward"></span></a></li>');
    });
}

function getPage(page_num, pid) {
    showSpinner();
    //Clear existing data
    $('#rows').html('');

    $('.listing').each(function () {
        $(this).toggleClass('active', false);
    });

    if (pid < 5) {
        $('.listing').each(function () {
            if (parseInt($('a', this).text()) <= pid + 5) {
                $(this).css("display", "inline");
            } else {
                $(this).css("display", "none");
            }
        });
    } else {
        $('.listing').each(function () {
            if (parseInt($('a', this).text()) <= pid + 5 && parseInt($('a', this).text()) > pid - 3) {
                $(this).css("display", "inline");
            } else {
                $(this).css("display", "none");
            }
        });
    }
    $('.pid' + pid).toggleClass('active', true);

    //Get subset of data
    $.get('/rest/usedPages?page=' + page_num + '&size=' + rows_per_page + '&filter=' + filterType, function (data) {
        //Loop through each row and output the data
        $(data.content).each(function () {
            var html_separator_hours;
            if (!this.separator_hours) {
                html_separator_hours = '';
            } else {
                html_separator_hours = '<li><span class="item-details-name">' + str_separator_hours + '</span>\n'
                    + '<span class="item-details-value">' + this.separator_hours + '</span></li>\n'
            }
            $('#rows').append(
                '<li>'
                + '<div class="col-sm-12 item-background">'
                + '<div class="row relative">'
                + '<div class="col-sm-4">'
                + '<img class="img-responsive img-thumbnail item-img" src="' + this.photo + '" alt="' +
                this.model + '" >'
                + '</div>'
                + '<div class="col-sm-8 relative">'
                + '<h4 class="model-name">' + this.model + '</h4>'
                + '<ul class="item-details">\n'
                + '<li><span class="item-details-name">' + str_year + '</span>\n'
                + '<span class="item-details-value">' + this.year + '</span></li>\n'
                + '<li><span class="item-details-name">' + str_location + '</span>\n'
                + '<span class="item-details-value">' + this.location + '</span></li>\n'
                + '<li><span class="item-details-name">' + str_engine_hours + '</span>\n'
                + '<span class="item-details-value">' + this.engine_hours + '</span></li>\n'
                + html_separator_hours
                + '<li><span class="item-details-name">ID: </span>\n'
                + '<span class="item-details-value">' + this.id + '</span></li>\n'
                + '</ul>\n'
                + '<div class="price col-sm-2 col-sm-offset-10">\n'
                + '<p class="pr-name">' + str_price + '</p>\n'
                + '<p class="pr-value">' + str_on_request + '</p>\n'
                + '</div>\n'
                + '<div class="col-sm-12 instr-div">\n'
                + '<p class="instruction">' + str_instructions + '</p>\n'
                + '</div>\n'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</li>');
        });
        hideSpinner();
    });
}

function setFilter(value) {
    filterType = value;
    initPageNumbers();

    var page_num = 0;
    if (window.location.hash !== '') {
        var hash_num = parseInt(window.location.hash.substring(1));
        if (hash_num > 0) {
            page_num = hash_num;
        }
    }

    getPage(page_num);
}

function setLocation(curLoc) {
    try {
        history.pushState(null, null, curLoc);
        return;
    } catch (e) {
    }
    location.hash = '#' + curLoc;
}

function getNextPage(isNext, last) {
    w_hash = window.location.hash.substring(1);
    if (isNext) {
        if (parseInt(w_hash) != last) {
            next = parseInt(w_hash) + 1;
            getPage(next, next);
            setLocation('#' + next);
        }
    } else {
        if (parseInt(w_hash) != 0) {
            prev = parseInt(w_hash) - 1;
            getPage(prev, prev);
            setLocation('#' + prev);
        }
    }
}

function showSpinner() {
    $("body").loadingModal({
        text: 'Please wait, results are loading... ',
        opacity: '0.9'
    });
    $("body").loadingModal('show');
}

function hideSpinner() {
    $("body").loadingModal('hide');

}

$(document).ready(function () {
    initLangVars();
    initPageNumbers();

    var page_num = 0;

    //If there's a hash fragment specifying a page number
    if (window.location.hash !== '') {
        //Get the hash fragment as an integer
        var hash_num = parseInt(window.location.hash.substring(1));

        //If the hash fragment integer is valid
        if (hash_num > 0) {
            //Overwrite the default page number with the user supplied number
            page_num = hash_num;
        }
    }

    //Load the first page
    getPage(page_num);
});