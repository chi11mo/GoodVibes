<template>
    <div class="grid-container">
        <div class="row">
            <div v-for="game in this.currentLastGames" class="col-md-6 col-xl-3">
                <div class="[ col-xs-12 col-sm-offset-2 col-sm-8 ]">
                    <ul class="event-list">
                        <li>
                            <time datetime="2014-07-20">
                                <span class="day">{{ game.day }}</span>
                                <span class="month">{{ game.month }}</span>
                                <span class="year">{{ game.year }}</span>
                                <span class="time">ALL DAY</span>
                            </time>
                            <div class="info">
                                <h2 class="title">{{ game.clubNameOne }} </h2>
                                <p class="desc">VS</p>
                                <h2 class="title">{{ game.clubNameTwo }}</h2>
                                <p class="desc"></p>
                            </div>
                            <div class="social">
                                <ul>
                                    <li class="facebook" style="width:33%;"><a href="#discord"><span
                                        class="bi bi-discord"><svg
                                        xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                        fill="currentColor" class="bi bi-discord"
                                        viewBox="0 0 16 16">
  <path
      d="M13.545 2.907a13.227 13.227 0 0 0-3.257-1.011.05.05 0 0 0-.052.025c-.141.25-.297.577-.406.833a12.19 12.19 0 0 0-3.658 0 8.258 8.258 0 0 0-.412-.833.051.051 0 0 0-.052-.025c-1.125.194-2.22.534-3.257 1.011a.041.041 0 0 0-.021.018C.356 6.024-.213 9.047.066 12.032c.001.014.01.028.021.037a13.276 13.276 0 0 0 3.995 2.02.05.05 0 0 0 .056-.019c.308-.42.582-.863.818-1.329a.05.05 0 0 0-.01-.059.051.051 0 0 0-.018-.011 8.875 8.875 0 0 1-1.248-.595.05.05 0 0 1-.02-.066.051.051 0 0 1 .015-.019c.084-.063.168-.129.248-.195a.05.05 0 0 1 .051-.007c2.619 1.196 5.454 1.196 8.041 0a.052.052 0 0 1 .053.007c.08.066.164.132.248.195a.051.051 0 0 1-.004.085 8.254 8.254 0 0 1-1.249.594.05.05 0 0 0-.03.03.052.052 0 0 0 .003.041c.24.465.515.909.817 1.329a.05.05 0 0 0 .056.019 13.235 13.235 0 0 0 4.001-2.02.049.049 0 0 0 .021-.037c.334-3.451-.559-6.449-2.366-9.106a.034.034 0 0 0-.02-.019Zm-8.198 7.307c-.789 0-1.438-.724-1.438-1.612 0-.889.637-1.613 1.438-1.613.807 0 1.45.73 1.438 1.613 0 .888-.637 1.612-1.438 1.612Zm5.316 0c-.788 0-1.438-.724-1.438-1.612 0-.889.637-1.613 1.438-1.613.807 0 1.451.73 1.438 1.613 0 .888-.631 1.612-1.438 1.612Z"/>
</svg></span></a></li>
                                    <li class="twitter" style="width:34%;"><a href="#twitter"><span
                                        class="fa fa-twitter"></span></a></li>
                                    <li class="google-plus" style="width:33%;"><a href="#google-plus"><span
                                        class="fa fa-google-plus"></span></a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props: ["currentLastGames"],
    name: "GameListPublic",

    data() {
        return {

            title: "",
            search: '',
        };
    },
}
</script>

<style scoped>
@import url("http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,400italic");
@import url("//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css");
.grid-container {
    width: 100%;
    display: grid;




}

.q-card {
    background-color: #f8f8f8;
    box-shadow: 0 0 3px #666;
    text-align: center;
}

.event-list {
    list-style: none;
    font-family: 'Lato', sans-serif;
    margin: 0px;
    padding: 0px;
height: 100%;
    width: 100%;

}
.event-list > li {
    background-color: #1D1D1D;
    color:rgb(255, 255, 255);
    box-shadow: 0px 0px 5px rgb(51, 51, 51);
    box-shadow: 0px 0px 5px rgba(51, 51, 51, 0.7);
    padding: 0px;
    margin: 0px 0px 20px;
}
.event-list > li > time {
    display: inline-block;
    width: 100%;
    color: #1D1D1D;
    background-color: rgb(255, 255, 255);
    padding: 5px;
    text-align: center;
    text-transform: uppercase;
}
.event-list > li:nth-child(even) > time {
    background-color: rgb(165, 82, 167);
}
.event-list > li > time > span {
    display: none;
}
.event-list > li > time > .day {
    display: block;
    font-size: 56pt;
    font-weight: 100;
    line-height: 1;
}
.event-list > li time > .month {
    display: block;
    font-size: 24pt;
    font-weight: 900;
    line-height: 1;
}
.event-list > li > img {
    width: 100%;
}
.event-list > li > .info {
    padding-top: 5px;
    text-align: center;
}
.event-list > li > .info > .title {
    font-size: 17pt;
    font-weight: 700;
    margin: 0px;
}
.event-list > li > .info > .desc {
    font-size: 13pt;
    font-weight: 300;
    margin: 0px;
}
.event-list > li > .info > ul,
.event-list > li > .social > ul {
    display: table;
    list-style: none;
    margin: 10px 0px 0px;
    padding: 0px;
    width: 100%;
    text-align: center;
}
.event-list > li > .social > ul {
    margin: 0px;
}
.event-list > li > .info > ul > li,
.event-list > li > .social > ul > li {
    display: table-cell;
    cursor: pointer;
    color: rgb(30, 30, 30);
    font-size: 11pt;
    font-weight: 300;
    padding: 3px 0px;
}
.event-list > li > .info > ul > li > a {
    display: block;
    width: 100%;
    color: rgb(30, 30, 30);
    text-decoration: none;
}
.event-list > li > .social > ul > li {
    padding: 0px;
}
.event-list > li > .social > ul > li > a {
    padding: 3px 0px;
}
.event-list > li > .info > ul > li:hover,
.event-list > li > .social > ul > li:hover {
    color: rgb(30, 30, 30);
    background-color: rgb(255, 255, 255);
}
.facebook a,
.twitter a,
.google-plus a {
    display: block;
    width: 100%;
    color: rgb(75, 110, 168) !important;
}
.twitter a {
    color: rgb(79, 213, 248) !important;
}
.google-plus a {
    color: rgb(221, 75, 57) !important;
}
.facebook:hover a {
    color: rgb(255, 255, 255) !important;
    background-color: rgb(75, 110, 168) !important;
}
.twitter:hover a {
    color: rgb(255, 255, 255) !important;
    background-color: rgb(79, 213, 248) !important;
}
.google-plus:hover a {
    color: rgb(255, 255, 255) !important;
    background-color: rgb(221, 75, 57) !important;
}
</style>
