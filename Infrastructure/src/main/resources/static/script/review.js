document.addEventListener('DOMContentLoaded', function () {
    const deleteReviewButtons = document.querySelectorAll('.review-item button#deleteReviewButton');
    deleteReviewButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();

            const reviewId = this.getAttribute('data-review-id');
            console.log('Review ID:', reviewId);

            if (confirm(`Are you sure you want to delete this review?`)) {
                fetch(`/api/review/delete/${reviewId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to delete review');
                    }
                    location.reload();
                })
                .catch(error => {
                    console.error('Error deleting review:', error);
                    alert('Failed to delete review. Please try again later.');
                });
            }
        });
    });
});
