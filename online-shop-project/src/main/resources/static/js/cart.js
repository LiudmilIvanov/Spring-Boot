  function sumAllProducts() {
          let elementArray = document.getElementById('tbody')
          let trElement = elementArray.childNodes[1]
          let tdElement = trElement.childNodes[3]
          let strongElement = tdElement.childNodes[0]

          let currentPRice = strongElement.textContent

          console.log(currentPRice)
          let totalPrice = document.getElementById('total')
      
      
          totalPrice.innerText = currentPRice
          console.log(totalPrice)
      }
      sumAllProducts()