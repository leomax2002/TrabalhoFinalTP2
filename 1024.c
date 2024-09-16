#include <stdio.h>

void tamanho_Palavra(char palavra[], int *tp){
	int i = 0;
	while(palavra[i] != '\0')
		i++;
	*tp = i;
}

int main(){
	int n, i, j;
	char m[1001];
	int tm;
	
	scanf("%d", &n);
	for(i = 0; i<n; i++){
		scanf(" %[^\n]s", m);
		tamanho_Palavra(m, &tm);
		for(j = 0; j<tm; j++){
			if( ((m[j]>=65) && (m[j]<=90)) || ((m[j]>=97)&&(m[j]<=122)) ){
				m[j] = m[j]+3;
			}
		}
		for(j = 0; j<=(tm-1)/2; j++){
			m[j] = m[j]-1;
		}
		for(j = tm; j>=0; j--){
			if(j>0)
				printf("%c", m[j-1]);
			else if(j == 0)
				printf("\n", m[j]);
		}
	}
	
	return 0;
}
