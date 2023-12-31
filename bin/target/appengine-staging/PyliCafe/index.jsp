<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modern Booking Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/css/intlTelInput.css">
    <!-- Add additional library for flags -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iti-libs/4.6.0/css/intlTelInput.min.css">
    <style>
       body {
           	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-image: url('https://lh3.googleusercontent.com/p/AF1QipOZlSJZuaCe0EZ0ubfCv7aM2-lICPeeuHvCH1IR=s680-w680-h510');
            background-size: cover; 
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #333;
        }

        label {
            display: block;
            margin: 15px 0 8px 0;
            color: #666;
            font-size: 16px;
            text-align: left; /* Align labels to the left */
        }

        input, select {
            width: calc(100% - 0px);
            padding: 10px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="date"] {
            width: calc(100% - 0px); /* Adjust width to align with other inputs */
        }

        input[type="tel"] {
            width: calc(100% - 0px); /* Adjust width to account for flag and prefix */
            padding-left: 10px; /* Ensure space for flag and prefix */
            box-sizing: border-box; /* Include padding and border in width calculation */
        }

        input[type="submit"] {
            background-color: #f56522;
            color: #fff;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 18px;
        }

        input[type="submit"]:hover {
            background-color: #db5b1f;
        }

        .iti {
            width: 100%;
        }
    </style>
</head>
<body>

    <form id="bookingForm">
        <h2>Book Your Experience</h2>

        <label for="name">Your Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="mobileNumber">Mobile Number:</label>
        <input type="tel" id="mobileNumber" name="mobileNumber" class="iti__input" required>

        <label for="eventId">Select Event:</label>
        <select id="eventId" name="eventId" required>
            <option value="" disabled selected>Select an event</option>
            <!-- Events will be dynamically added here -->
        </select>

        <label for="isoDate">Date of Booking:</label>
        <input type="date" id="isoDate" name="isoDate" required>

        <input type="submit" value="Book Now">
    </form>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/iti-libs/4.6.0/js/intlTelInput.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var eventDropdown = document.getElementById("eventId");
            var bookingForm = document.getElementById("bookingForm");

            // Use your endpoint to fetch events from the server
            var url = "/../external/api/event";

            // Set your organization ID here
            var orgId = "PYLI";

            // Initialize the intl-tel-input library
            var input = document.querySelector("#mobileNumber");
            var iti = window.intlTelInput(input, {
                separateDialCode: true,
                initialCountry: "GR",
                utilsScript: "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js"
            });

            fetch(url, {
                headers: {
                    'orgId': orgId
                }
            })
            .then(response => response.json())
            .then(data => {
                if (data.success && data.responseBody && data.responseBody.events) {
                    data.responseBody.events.forEach(event => {
                        var option = document.createElement("option");
                        option.value = event.id; // Set the appropriate value for each event
                        option.text = event.name; // Set the appropriate display text for each event
                        eventDropdown.add(option);
                    });
                } else {
                    console.error("Error: Unable to fetch events. Response:", data);
                }
            })
            .catch(error => console.error("Error fetching events:", error));
            
            bookingForm.addEventListener("submit", function(event) {
                event.preventDefault(); // Prevent the default form submission

                // Collect form data
                var formData = new FormData(bookingForm);
                formData.append("orgId", orgId);

                // Perform AJAX submission
                fetch("../external/api/reservation ", {
                    method: "POST",
                    headers: {
                        'orgId': orgId,
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(Object.fromEntries(formData.entries()))
                })
                .then(response => response.json())
                .then(data => {
                    // Handle the response from the server
                    console.log(data);
                
                    if (data.success) {
                        // Redirect to the thank you page
                        window.location.href = "thankYou.html"; // Replace with the actual path to your thank you page
                    } else {
                        console.error("Form submission failed. Response:", data);
                        // Optionally display an error message or handle the failure in another way
                    }
                })
                .catch(error => console.error("Error submitting form:", error));
            });
        });

        // Convert date to ISO format before form submission
        document.getElementById("isoDate").addEventListener("change", function() {
            var selectedDate = new Date(this.value);
            var isoDate = selectedDate.toISOString().split('T')[0];
            this.value = isoDate;
        });
    </script>

</body>
</html>
