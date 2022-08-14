<h1>Binance AutoCatch</h1>
Nearly 3-4 times a month, <strong>Binance</strong> used to list a new coin or incorporate existing coins into it's collection. Whenever Binance proceeded with incorporation of a coin or a token, that token was bound to gain at least <strong>25%</strong> on it's existing value. I did some research on the previously listed coins and I found that minimum gain was always <strong>25%</strong> and highest gain went beyond <strong>100%</strong>. So, I saw an opportunity. Buying that coin before it was listed on Binance.

But obviously it wasn't so simple. Here are a few reasons why:
<ol>
 	<li>Binance was a bit random with the listing dates.</li>
 	<li>Binance used to announce the listings on it's website, Twitter and Telegram channel just 2-3 minutes before the actual listing. Giving people very little to no time to go ahead and buy that coin.</li>
 	<li>I had no idea about their coin selection process so I could buy those coins in advance.</li>
</ol>
And after spending a day thinking it through, I realized that it's close to impossible for a human to physically keep up with a random schedule among so many other factors.

So, I decided to automate this process for me. Here is an overview of steps I had to go through to get it done:
<h1>Tech Stack Used</h1>
<ul>
 	<li><strong>Linux Server</strong></li>
 	<li><strong>Spring Boot</strong></li>
 	<li><strong>MySQL</strong></li>
 	<li><strong>Docker</strong></li>
 	<li><strong>Jenkins</strong></li>
 	<li><strong>Android Native</strong></li>
 	<li><strong>Git</strong></li>
</ul>
<h1>How It Works</h1>
<ol>
 	<li><strong>Binance Scraping:</strong> First, I had to scrape the Binance's announcement page to get the info of newly listed coin. Interval of scraping was 10 seconds (which was changeable from mobile end)</li>
 	<li><strong>Formatting Data: </strong>After fetching the data, I formatted it until I was left with the coin/token symbol and contract address.</li>
 	<li><strong>Quote Fetching: </strong>Now, I needed to take that formatted data and fetch a quote to convert my existing stable coin to the newly listed currency. Stable token depended on the blockchain technology of the newly listed coin/token and I used <a href="https://www.0x.org/" target="_blank" rel="noopener">0xAPI</a> to fetch the quote.</li>
 	<li><strong>Signing the Transaction:</strong> Finally I setup my own nodes on <a href="https://moralis.io/" target="_blank" rel="noopener">Moralis</a> to sign the transactions through my server.</li>
 	<li><strong>Waiting:</strong> At last, I had to wait for the transaction to come through. I scanned the <a href="https://bscscan.com/" target="_blank" rel="noopener">bscscan</a> or <a href="https://etherscan.io/" target="_blank" rel="noopener">etherscan</a> (depending on the listing token's blockchain) to fetch the transaction status.</li>
</ol>
I made an <strong>Android</strong> app to monitor the entire process from start to finish and used <strong>FCM</strong> to stay updated at the mobile end.

Entire process from fetching the newly listed coin to performing a swap, took around <strong>6-15 seconds</strong>. Which was way faster than I was expecting. Buying a currency within the first <strong>20 seconds</strong> of listing was quite decent.

<h1 id="license">License :page_facing_up:</h1>

Copyright 2022 Manpreet Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this software except in compliance with the License.
You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
