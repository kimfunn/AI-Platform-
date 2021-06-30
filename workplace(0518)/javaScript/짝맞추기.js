var 가로 = 4;
var 세로 = 3;
var 색깔후보 = ['red', 'red', 'orange','orange','green','green','yellow','yellow','white','white','pink','pink'];
var 색깔 = [];
var 클릭플래그 = true;
var 클릭카드 = [];
var 완성카드 = [];

for (var i = 0; 색깔후보.length > 0; i +=1) {
    색깔 = 색깔.concat(색깔후보.splice(Math.floor(Math.random() * 색깔후보.length), 1));
}
console.log(색깔);

var Title = document.createElement( 'h1' );
var Text = document.createTextNode( '카드 맞추기 게임' );
Title.appendChild( Text );
document.body.appendChild( Title );

function 카드세팅(가로, 세로) {
    클릭플래그 = false;
    for (var i = 0; i< 가로 * 세로; i += 1){
        var card = document.createElement('div');
        card.className = 'card';
        var cardInner = document.createElement('div');
        cardInner.className = 'card-inner';
        var cardFront = document.createElement('div');
        cardFront.className = 'card-front';
        var cardBack = document.createElement('div');
        cardBack.className = 'card-back';
        cardBack.style.backgroundColor = 색깔[i];
        cardInner.appendChild(cardFront);
        cardInner.appendChild(cardBack);
        card.appendChild(cardInner);
        (function (c) {
            card.addEventListener('click', function () {
                if (클릭플래그 && !완성카드.includes(c)) { // 클랙플래그가 true 고 완성카드가 아닐때만 클릭 가능
                    c.classList.toggle('flipped');
                    클릭카드.push(c);
                    if (클릭카드.length === 2) {
                        if (클릭카드[0].querySelector('.card-back').style.backgroundColor === 클릭카드[1].querySelector('.card-back').style.backgroundColor) {
                            완성카드.push(클릭카드[0]);
                            완성카드.push(클릭카드[1]);
                            클릭카드 = [];  // 클릭카드 0 1 이 색이 같다면 완성카드 배열에 삽입 후 클릭카드 
                            if (완성카드.length === 12) {
                                alert('축하합니다');
                            }
                        } else { // 클린한 두 카드의 색이 다르다면 클릭카드 0 1 플립 삭제 후, 클리카드 초기화
                            클릭플래그 = false;
                            setTimeout(function () {
                                클릭카드[0].classList.remove('flipped');
                                클릭카드[1].classList.remove('flipped');
                                클릭플래그 = true;
                                클릭카드 = [];                                
                            }, 1000);
                        }
                    }
                }  // 클릭 플래그가 true 일때만 클릭이 가능하도록 현재 false로 클릭불가
            });
        })(card);
        document.body.appendChild(card);
    }  

    document.querySelectorAll('.card').forEach(function(card, index) {
        setTimeout(function () {
            card.classList.add('flipped');
        }, 1000 + 100 * index);
        // 시간차를 두고 카드를 순차적으로 보여줌 (settimeout설정을 이용)      
        // 처음부터 카드가 다 열려 있게해주는 코드
    });
    
    setTimeout(function () {
        document.querySelectorAll('.card').forEach(function (card, index) {
            card.classList.remove('flipped');
        });
        클릭플래그 = true; // 뒷면 색이 모두 패치되어 전부 보여준 후 닫아 버린 다음에 클릭 가능
    }, 5000);
    // 모든 카드를 5000초 *5초 정도 보여주고 다시 다 닫아버리는 코드
    // 추가로 여기서 클릭플래그 를 이용하여 카드가 보여지는 와중에 클릭을 할 수없도록 
    // 조건을 걸어줄 필요가 있다.
}

카드세팅(가로, 세로);