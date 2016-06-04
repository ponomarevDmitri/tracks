/**
 * Created by dima-pc on 17.05.2016.
 */

var map;

function initUserMap(mapElementName){
    map = new google.maps.Map(document.getElementById(mapElementName), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 8
    });
}