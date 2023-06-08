#include <iostream>
#include <iostream>
#include <cstring>
#include <openssl/rsa.h>
#include <openssl/pem.h>

// �T������
#define MSG_LEN 64

// ���ͤ��_�M�p�_
RSA* generateKeyPair() {
    RSA* keypair = RSA_new();
    BIGNUM* e = BN_new();
    int bits = 2048;
    unsigned long exponent = RSA_F4;
    BN_set_word(e, exponent);
    RSA_generate_key_ex(keypair, bits, e, nullptr);
    return keypair;
}

// �ϥ� RSA ���_�[�K�T��
std::string encrypt(RSA* public_key, const char* msg) {
    int rsa_len = RSA_size(public_key);
    int msg_len = strlen(msg);
    char* encrypted = new char[rsa_len + 1];
    memset(encrypted, 0, rsa_len + 1);
    int ret = RSA_public_encrypt(msg_len, reinterpret_cast<const unsigned char*>(msg),
                                 reinterpret_cast<unsigned char*>(encrypted), public_key, RSA_PKCS1_OAEP_PADDING);
    if (ret < 0) {
        std::cerr << "�[�K����" << std::endl;
        return "";
    }
    std::string res(encrypted, ret);
    delete[] encrypted;
    return res;
}

// �ϥ� RSA �p�_�ѱK�T��
std::string decrypt(RSA* private_key, const char* msg) {
    int rsa_len = RSA_size(private_key);
    int msg_len = strlen(msg);
    char* decrypted = new char[rsa_len + 1];
    memset(decrypted, 0, rsa_len + 1);
    int ret = RSA_private_decrypt(msg_len, reinterpret_cast<const unsigned char*>(msg),
                                  reinterpret_cast<unsigned char*>(decrypted), private_key, RSA_PKCS1_OAEP_PADDING);
    if (ret < 0) {
        std::cerr << "�ѱK����" << std::endl;
        return "";
    }
    std::string res(decrypted, ret);
    delete[] decrypted;
    return res;
}

int main() {
    // ���ͤ��_�M�p�_
    RSA* keypair = generateKeyPair();

    // ���ե[�K�M�ѱK
    const char* msg = "Hello, world!";
    std::cout << "��l�T���G" << msg << std::endl;
    std::string encrypted = encrypt(keypair, msg);
    std::cout << "�[�K��T���G" << encrypted << std::endl;
    std::string decrypted = decrypt(keypair, encrypted.c_str());
    std::cout << "�ѱK��T���G" << decrypted << std::endl;

    // ����귽
    RSA_free(keypair);

    return 0;
}