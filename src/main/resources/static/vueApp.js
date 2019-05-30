const url = "./characters";

const vm = new Vue({
    el: '#app',
    data: {
        results: [],
        season: 1,
        checked: false
    },
    watch: {
        season: 'updateSeason'
    },
    methods: {
        updateSeason: function() {
            //alert(this.season);
            axios.get(url + "?season=" + this.season).then(response => {
                this.results = response.data;
            }).catch(error => {
                alert(error.response.data.message)
            });
        }
    },
    mounted() {
        axios.get(url).then(response => {
        console.log(response.data);
            this.results = response.data
        })
    }
});