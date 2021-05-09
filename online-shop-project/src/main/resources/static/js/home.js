let elements = document.getElementsByClassName('card-img-top');

for (let index = 0; index < elements.length; index++) {
	let img = elements[index];

	img.addEventListener("mouseover", function(e) {

		img.style.height = '220px';
		img.style.width = '240px';

		console.log(e.currentTarget)
	})

	img.addEventListener('mouseout', function(e) {
		img.style.height = '200px';
		img.style.width = '225px';
	})

}

let element = document.getElementById('quote')
let arrayOfQuotes = [];
//let count = 0;

arrayOfQuotes.push('“Tea is a part of daily life. It is as simple as eating when hungry and drinking when thirsty.” – Yamamoto Soshun')
arrayOfQuotes.push('“No matter where you are in the world, you are at home when tea is served.” – Earlene Grey')
arrayOfQuotes.push('“There is something in the nature of tea that leads us into a world of quiet contemplation of life.” – Lin Yutang')

function display(count) {
	if (count >= arrayOfQuotes.length) {
		count = 0;
	}

	element.innerText = arrayOfQuotes[count];
	setTimeout(function() {
		display(count + 1)
	}, 4000);
}
display(0);

console.log(arrayOfQuotes)
