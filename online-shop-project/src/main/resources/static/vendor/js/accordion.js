function toggle() {
	    let btn = document.getElementsByClassName('button')[0];
	    let info = document.getElementById('extra')

	    if (btn.textContent === 'More details') {
	        info.style.display = 'block'
			console.log(info.style.display)
	        btn.textContent = 'Less details'
	    } else {
	        btn.textContent = 'More details'
	        info.style.display = 'none'
			console.log(info.style.display)
	    }

	    
	    console.log(info)

	}