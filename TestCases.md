# Test Cases

## Common for Android and iOS

- On Initial Load, check that the number of deliveries should be equal to 20
- Test Infinite scroll, but scrolling till last element of Initial load, 20 more deliveries should be loaded
- If internet is off, on Initial load, user should see some meaningful error message
- Turn off internet after initial load and test infinite scroll, user should see some meaningful error message
- Click on a single delivery cell/item, delivery details page with map should be loaded
- On delivery details page, click back, user should see the initial delivery list items
- On delivery details page, turn off the internet and click on back, user should see some meaningful error message

## Android specific

- Turn off internet after initial load, and pull down to refresh the page, user should see some meaningful error message



## iOS specific
