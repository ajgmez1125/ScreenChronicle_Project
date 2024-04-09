document.addEventListener("DOMContentLoaded", function () {
    var addToWatchlistLink = document.getElementById("addToWatchlist");
    addToWatchlistLink.addEventListener("click", function (event) {
        event.preventDefault(); // Prevent default link behavior
        var movieId = addToWatchlistLink.getAttribute("data-movie-id");
        fetch("/api/watchlist/add/" + movieId)
        .then(function(response) {
            if (response.ok) {
                window.location.href = "/movie/" + movieId;
            } else {
                console.log("Error occurred while adding to watchlist.");
            }
        })
        .catch(function(error) {
            console.log("Fetch error:", error);
        });
    });
});
