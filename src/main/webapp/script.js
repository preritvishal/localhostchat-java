function scrollToBottom() {
    const messageList = document.getElementById("previous-text-div");
    messageList.scrollTop = messageList.scrollHeight;
}


function getMessages() {	// original
     scrollToBottom();
  fetch("./chat") // Replace with your servlet URL
    .then(response => response.json())
    .then(data => {
      const messageList = document.getElementById("previous-text-div");
      messageList.innerHTML = ""; // Clear existing messages
      
      data.forEach(message => {
        const messageElement = document.createElement("p");
        messageElement.textContent = message;
        messageList.appendChild(message);
      });
      
    })
    .catch(error => {
      console.error("Error fetching messages:", error);
    });
}

window.onload = function () {
    const messageInput = document.getElementById("message-input");
    messageInput.focus();
    getMessages();
    scrollToBottom();
};

setInterval(getMessages, 500);	// repeating it every half second to keep it updated!
