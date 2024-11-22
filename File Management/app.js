class FileManager {
    constructor() {
        this.files = []; // Array to store file metadata
    }

    // Create a file
    createFile(name, content) {
        return new Promise((resolve, reject) => {
            if (!name || !content) {
                reject('File name and content cannot be empty.');
                return;
            }
            const newFile = { name, content };
            this.files.push(newFile);
            resolve('File created successfully.');
        });
    }

    // Upload a file (simulate upload by adding to files list)
    uploadFile(file) {
        return new Promise((resolve, reject) => {
            if (!file) {
                reject('No file selected for upload.');
                return;
            }
            this.files.push(file);
            resolve('File uploaded successfully.');
        });
    }

    // Download a file (simulate download by returning file content)
    downloadFile(fileName) {
        return new Promise((resolve, reject) => {
            const file = this.files.find(f => f.name === fileName);
            if (!file) {
                reject('File not found.');
                return;
            }
            resolve(file.content);
        });
    }

    // Delete a file
    deleteFile(fileName) {
        return new Promise((resolve, reject) => {
            const fileIndex = this.files.findIndex(f => f.name === fileName);
            if (fileIndex === -1) {
                reject('File not found.');
                return;
            }
            this.files.splice(fileIndex, 1);
            resolve('File deleted successfully.');
        });
    }

    // Show file names
    showFileNames() {
        return new Promise((resolve, reject) => {
            if (this.files.length === 0) {
                reject('No files to display.');
                return;
            }
            resolve(this.files.map(f => f.name));
        });
    }
}

let fileManager = new FileManager();

// Create File
function createFile() {
    const name = prompt('Enter file name:');
    const content = prompt('Enter file content:');
    fileManager.createFile(name, content)
        .then(message => {
            alert(message);
            showFiles(); // Update file list
        })
        .catch(error => {
            showError(error);
        });
}

// Upload File
function uploadFile() {
    const fileName = prompt('Enter file name for upload:');
    const fileContent = prompt('Enter file content for upload:');
    const file = { name: fileName, content: fileContent };
    fileManager.uploadFile(file)
        .then(message => {
            alert(message);
            showFiles(); // Update file list
        })
        .catch(error => {
            showError(error);
        });
}

// Download File
function downloadFile() {
    const fileName = prompt('Enter file name to download:');
    fileManager.downloadFile(fileName)
        .then(content => {
            alert('File content: ' + content);
        })
        .catch(error => {
            showError(error);
        });
}

// Delete File
function deleteFile() {
    const fileName = prompt('Enter file name to delete:');
    fileManager.deleteFile(fileName)
        .then(message => {
            alert(message);
            showFiles(); // Update file list
        })
        .catch(error => {
            showError(error);
        });
}

// Show Files
function showFiles() {
    fileManager.showFileNames()
        .then(files => {
            const fileList = document.getElementById('file-list');
            fileList.innerHTML = files.map(file => `<li>${file}</li>`).join('');
        })
        .catch(error => {
            showError(error);
        });
}

// Display error message
function showError(message) {
    document.getElementById('error-message').innerText = message;
}

document.addEventListener('contextmenu', (event) => {
    event.preventDefault();
    const fileName = event.target.innerText;
    const menu = document.createElement('div');
    menu.className = 'context-menu';
    menu.style.position = 'absolute';
    menu.style.top = `${event.pageY}px`;
    menu.style.left = `${event.pageX}px`;
    menu.innerHTML = `
        <ul>
            <li onclick="downloadFileByName('${fileName}')">Download</li>
            <li onclick="deleteFileByName('${fileName}')">Delete</li>
        </ul>
    `;
    document.body.appendChild(menu);

    window.addEventListener('click', () => {
        menu.remove();
    });
});

function downloadFileByName(fileName) {
    fileManager.downloadFile(fileName)
        .then(content => {
            alert('File content: ' + content);
        })
        .catch(error => {
            showError(error);
        });
}

function deleteFileByName(fileName) {
    fileManager.deleteFile(fileName)
        .then(message => {
            alert(message);
            showFiles(); // Update file list
        })
        .catch(error => {
            showError(error);
        });
}


