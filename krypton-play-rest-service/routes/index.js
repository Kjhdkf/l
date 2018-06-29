var express = require('express');
var path = require('path');
var router = express.Router();
var Users = require('../database/users');

router.get('/', function(req, res, next) {
  res.render('index')
});

/** properties */

/** validate user and return boolean */
function validateUser(req) {
    let auth = (req.headers.authorization || '').split(' ')[1] || '';

    if(!auth)
        return false;

    let user  = new Buffer(auth, 'base64').toString().split(':');

    console.log("username " + user[0])
    console.log("password " + user[1])

    if(user[0] === 'krypton' && user[1] === 'krypton')
        return true;

    return false;
}

/** routers */
router.get('/basicauth', (req, res, next) => {
     let userIsValid = validateUser(req)

	if(!userIsValid){
		res.statusCode = 401;
		res.send();
		return;
	}

    res.send();
});

router.get('/authentication', (req, res, next) => {
     let userIsValid = validateUser(req)

	if(!userIsValid){
		res.statusCode = 401;
		res.setHeader('WWW-Authenticate', 'Basic realm="Example"');
		res.end('Access denied');
		return;
    }

    res.send("<div id='authontication'>Hello Krypton User</div>")
});

router.get('/users', (req, res, next) => {
    res.send(Users._list);
});

router.post('/user', (req, res, next) => {
    try{
        let nUser = new Users(req.body.id, 
            req.body.name, 
            req.body.age, 
            req.body.email);

        nUser.save();

        res.send("user added")
    }catch(err) {
        console.error(err)
        res.status(400).send(err.message);
    }
});

router.delete('/user', (req, res, next) => {
    try{
        let user = new Users(Number(req.query.id));
        user.delete();

        res.send("user deleted");
    }catch(err) {
        console.error(err)
        res.status(400).send(err.message);
    }
});


router.put('/user', (req, res, next) => {
    try {
        let nUser = new Users(req.body.id, 
            req.body.name, 
            req.body.age, 
            req.body.email);

        nUser.update();

        res.send("user updated");
    }catch(err) {
        console.error(err)
        res.status(400).send(err.message);
    }
});

router.get('/download_excel', (req, res, next) => {
    let filePath = path.join(__dirname, "../", "files/sample excel.xlsx");
    res.download(filePath);
});

router.get('/download_text', (req, res, next) => {
    let filePath = path.join(__dirname, "../", "files/sample text.txt");
    res.download(filePath);
})


module.exports = router;
