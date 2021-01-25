"use strict";

const {
    openBrowser,
    write,
    closeBrowser,
    goto,
    below,
    press,
    screenshot,
    above,
    click,
    checkBox,
    listItem,
    toLeftOf,
    link,
    text,
    into,
    textBox,
    evaluate,
    waitFor,
    dismiss,
    accept,
    alert
} = require('taiko');
const assert = require("assert");

const username = 'THWDWwadaI';
const password = 'India456';


function makeid(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
       result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
 }

// --------------------------------------------
/*
step("Go to page <weburl> and order <product>", async (weburl,product) => {

        try {
            // await openBrowser()
            await goto(weburl);
            await click(product);
            // await alert('Product added', async () => await dismiss());
            await alert("Product added", accept); 
            await click('Add to cart');
            //await waitFor(5000)
            await click('Cart') 
            await waitFor(1000)  
            await click('Place Order')
            //await waitFor(10000)   
            await waitFor(1000) 
            await write('Parth', into(textBox({id:'name'})))
            //await write('Parth', into(textBox("Name:")))
      
            await write('Germany',  into(textBox({id:'country'})))
            await write('Frankfurt',  into(textBox({id:'city'})))
            await write('4716655219712534',  into(textBox({id:'card'})))  
            await write('12',  into(textBox({id:'month'})))
            await write('2021', into(textBox({id:'year'})))
            /*
            await write('Germany', into(textBox("Country:")))
            await write('Frankfurt', into(textBox("City:")))
            await write('4716655219712534', into(textBox("Credit card:")) )        
            await write('12', into(textBox("Month:")))
            await write('2021', into(textBox("Year:")))   
            
            await click('Purchase')            
            await waitFor(1000)   
            await click('OK')       
        } catch (error) {
            console.error(error);
        } finally {
            //await closeBrowser();
        }
    });

*/

step("Go to page <weburl> and order <product>", async (weburl,product) => {

    try {

        await goto(weburl);
        await click('Sign up')
        await waitFor(100)

        var uname = makeid(6) ;
        console.log('Username is :'+ uname)
        await write(uname, into(textBox({id:'sign-username'})))
        await write(password, into(textBox({id:'sign-password'})))
        await waitFor(1000)
        await alert("Sign up successful.", accept)
        //await alert("Sign up succesful", accept);
        await click('Sign up', below("Sign up"))
        await click('OK');
        await click('Log in')
        await waitFor(1000)
        await write(uname, into(textBox({id:'loginusername'})))
        await write(password, into(textBox({id:'loginpassword'})))
        await waitFor(1000)
        await click('Log in', below("Log in"))
        await waitFor(3000)

       // var user_exist = await text('Welcome' + ' '+ uname).exists()
       var user_exist = await text( new RegExp(uname) ).exists() 
       
        if (user_exist) {
          console.log('User '+ uname + ' succesfully logged in')     
        } else {
          console.log('Login Unsuccessful for User '+ uname )     
        }
       await waitFor(3000)

        await click('Log out')
        await waitFor(3000)
        


    } catch (error) {
        console.error(error);
    } finally {
        //await closeBrowser();
    }
});

