<template>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous"
    />
    <title>League Table</title>
  </head>
  <body>

  <div id="club-list">
    <div>
      <b-table :items="clubs" :fields="clubs" :tbody-tr-class="rowClass"></b-table>
    </div>
  <section class="content-info">
    <div class="container paddings-mini">
      <div class="row">
        <div class="col-lg-12">
          <table
              class="table-striped table-responsive table-hover result-point"
          >
            <thead class="point-table-head">
            <tr>
              <th class="text-left">No</th>
              <th class="text-left">TEAM</th>
              <th class="text-center">P</th>
              <th class="text-center">W</th>
              <th class="text-center">D</th>
              <th class="text-center">L</th>
              <th class="text-center">GS</th>
              <th class="text-center">GA</th>
              <th class="text-center">+/-</th>
              <th class="text-center">PTS</th>
            </tr>
            </thead>
            <tbody class="text-center">
            <div v-for="(club, index) in clubs"
                 :key="index">
              <td class="text-left number">
                {{ index + 1 }} <i class="fa fa-caret-up" aria-hidden="true"></i>
              </td>
              <td>
                <span>{{ club.team }}</span>
              </td>
              <td>{{ club.plays }}</td>
              <td>{{ club.wins }}</td>
              <td>{{ club.loses}}</td>
              <td>{{ club.defines }}</td>
              <td>{{ club.goals }}</td>
              <td>{{ club.goalsAgainst }}</td>
              <td>{{ club.goalDiff }}</td>
              <td>{{ club.points }}</td>
            </div>
            </tbody>
          </table>
        </div>
      </div>
    </div>

  </section>
  </div>
  </body>
  </html>
</template>

<script>

import ClubService from "../services/ClubService";
export default {
  name: "club-list",
  data() {

    return {
      clubs: [],
      twitchUrl: "https://www.twitch.tv/",
      currentClub: null,
      currentIndex: -1,
      title: ""
    };
  },
  mounted() {
    this.retrieveClubs();
  },
  beforeMount(){
    this.retrieveClubs();
  },
  created() {
    this.retrieveClubs();
  },
  methods: {
    retrieveClubs() {
     ClubService.getClubs()
          .then(response => {
            this.clubs = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },
    refreshList() {
      this.retrieveClubs();
      this.currentClub = null;
      this.currentIndex = -1;
    },
    setActiveClub(club, index) {
      this.currentClub = club;
      this.currentIndex = index;
    },

  }
};
</script>

<style scoped>
.selector-for-some-widget {
  box-sizing: content-box;
}

body {
  padding-top: 60px;
  font-family: "Saira Semi Condensed", sans-serif;
  font-weight: 400;
  background-color: white;

}

.content-info {
  background: #f9f9f9;
  padding: 40px 0;
  background-size: cover !important;
  background-position: top center !important;
  background-repeat: no-repeat !important;
  position: relative;
  padding-bottom: 100px
}

table {
  width: 100%;
  background: #fff;
  border: 1px solid #dedede

}

table thead tr th {
  padding: 20px;
  border: 1px solid #dedede;
  color: #000
}

table.table-striped tbody tr:nth-of-type(odd) {
  background: #e01515
}

table.result-point tr td.number {
  width: 100px;
  position: relative
}

.text-left {
  text-align: left !important
}

table tr td {
  padding: 10px 20px;
  border: 1px solid #dedede
}

table.result-point tr td .fa.fa-caret-up {
  color: green
}

table.result-point tr td .fa {
  font-size: 20px;
  position: absolute;
  right: 20px
}

table tr td {
  padding: 10px 40px;
  border: 1px solid #dedede
}

table tr td img {
  max-width: 32px;
  float: left;
  margin-right: 11px;
  margin-top: 1px;
  border: 1px solid #dedede
}

/* Add a black background color to the top navigation */
.topnav {
  background-color: #333;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: #04AA6D;
  color: white;
}

</style>